import './App.css'
import React, {useEffect, useState} from "react";

type Status = "PENDING" | "APPROVED" | "DRAFT";
const Status = {
    PENDING: "PENDING",
    APPROVED: "APPROVED",
    DRAFT: "DRAFT"
} as const;

type ExpenseProp = {
    expenseId: string,
    description: string,
    amount: number,
    status: Status,
    expenseDate: string
}


const Expense = (expense: ExpenseProp) => {
    return (
        <li>{expense.expenseId}</li>
    );
}

function App() {
    const [list, setList] = useState<ExpenseProp[]>([]);
    const [filter, setFilter] = useState<Status>(Status.APPROVED);

    const getData: () => Promise<void> = async () => {
        const response: Array<ExpenseProp> = await fetch("http://localhost:8443/get?status=" + filter, { method: "GET" })
            .then((res: Response) =>  res.json());
        setList(response);
    }

    const onFilterChange = (e: React.ChangeEvent<HTMLSelectElement>) => {
        setFilter(e.target.value as Status);
    }

    useEffect(()=> {
        getData();
    }, [filter])

    return (
        <>
            <label>Expense List</label>
            <select id="filter" value={filter} onChange={onFilterChange}>
                <option value={Status.APPROVED}>Approved</option>
                <option value={Status.PENDING}>Pending</option>
                <option value={Status.DRAFT}>Draft</option>
            </select>
            <ul>
                {list.map((expense: ExpenseProp) => <Expense key={expense.expenseId} {...expense}/>)}
            </ul>
        </>
    )
}

export default App
