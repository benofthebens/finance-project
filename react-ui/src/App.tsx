import './App.css'
import {useEffect, useState} from "react";
import {useKeycloak} from "@react-keycloak/web";
import type Keycloak from "keycloak-js";

type Status = "PENDING" | "APPROVED" | "DRAFT";
const Status = {
    PENDING: "PENDING",
    APPROVED: "APPROVED",
    DRAFT: "DRAFT"
} as const;

type Expense = {
    expenseId: string,
    userId: string,
    description: string,
    amount: number,
    status: Status,
    expenseDate: string
}

const getExpenses: (status: Status, keycloak: Keycloak) => Promise<Array<Expense>> = async (status: Status, keycloak: Keycloak) => {
    const expenses: Array<Expense> = await fetch(`http://localhost:8443/api/expense?userId=${keycloak.subject}&status=${status}`, {
        method: "GET",
        headers: {
            Authorization: `Bearer ${keycloak.token}`,
        }
    })
        .then((res: Response) =>  res.json()).catch()

    return expenses;
}

const createExpense: (expense: Expense, authToken: string) => Promise<void> = async (expense: Expense, authToken: string) => {
    const response = await fetch(`http://localhost:8443/api/expense`, {
        method: "POST",
        headers: {
            Authorization: `Bearer ${authToken}`,
            "Content-Type": "application/json"
        },
        body: JSON.stringify(expense),
    });

    if (!response.ok) {
        console.log("oh no");
    }
}

const Expense= (expense: Expense) => {
    return (
        <tr>
            <td>{expense.expenseId}</td>
            <td>{expense.description}</td>
            <td>{expense.expenseDate}</td>
            <td>{expense.amount}</td>
            <td>{expense.status}</td>
        </tr>
    );
}

function ExpenseForm() {
    const {keycloak} = useKeycloak();

    const [description, setDescription] = useState<string>("");
    const [amount, setAmount] = useState<number>(0);
    const [status, setStatus] = useState<Status>(Status.APPROVED);
    const [expenseDate, setExpenseDate] = useState<string>("");

    const handleSubmit = (e: React.FormEvent) => {
        e.preventDefault();
        const expense: Expense = {
            expenseId: "",
            userId: keycloak.subject == undefined ? "" : keycloak.subject,
            description: description,
            amount: amount,
            status: status,
            expenseDate: expenseDate
        }
        createExpense(expense, (keycloak.token == undefined) ? "" : keycloak.token);
    }

    return (
        <form onSubmit={handleSubmit}>
            <label>Description: </label>
            <input type="text" value={description} onChange={(e) => setDescription(e.target.value)}/>

            <label>Amount: </label>
            <input type="number" value={amount} onChange={(e) => setAmount(e.target.value as unknown as number)}/>

            <select id="filter" value={status} onChange={(s) => setStatus(s.target.value as Status)}>
                <option value={Status.APPROVED}>Approved</option>
                <option value={Status.PENDING}>Pending</option>
                <option value={Status.DRAFT}>Draft</option>
            </select>

            <label>Date:</label>
            <input type="date" value={expenseDate} onChange={(e) => setExpenseDate(e.target.value)}/>
            <button type="submit">Create Expense</button>
        </form>
    )
}

function App() {
    const [list, setList] = useState<Expense[]>([]);
    const {keycloak, initialized} = useKeycloak();
    const [filter, setFilter] = useState<Status>(Status.APPROVED);

    useEffect(() => {
        if (!initialized) {
            return;
        }

        getExpenses(filter, keycloak).then(r => r != undefined ? setList(r) : setList([]));

    }, [filter, initialized])

    return (
        <>
            <label>Expense Filter: </label>

            <select id="filter" value={filter} onChange={(s) => setFilter(s.target.value as Status)}>
                <option value={Status.APPROVED}>Approved</option>
                <option value={Status.PENDING}>Pending</option>
                <option value={Status.DRAFT}>Draft</option>
            </select>
            <ExpenseForm/>
            <table>
                <thead>
                </thead>
                <tbody>
                    {list.map((expense: Expense) => (<Expense key={expense.expenseId} {...expense}/>))}
                </tbody>
            </table>
        </>
    )
}


export default App
