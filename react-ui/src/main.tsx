import { createRoot } from 'react-dom/client'
import keycloak from './Keycloak.ts'
import './index.css'
import App from './App.tsx'
import {ReactKeycloakProvider} from "@react-keycloak/web";

createRoot(document.getElementById('root')!).render(
    <ReactKeycloakProvider authClient={keycloak} initOptions={{onLoad: "login-required"}}>
        <App />
    </ReactKeycloakProvider>
)
