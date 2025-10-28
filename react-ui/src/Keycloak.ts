import Keycloak from 'keycloak-js'

const keycloak = new Keycloak({
    url: 'http://keycloak:8080',
    realm: 'finance-application',
    clientId: 'react-client'
});

export default keycloak;