## Spring Security OAuth 2 Example

This module demonstrates OAuth authorization flow using Spring Authorization Server, Spring OAuth Resource Server and
Spring OAuth Client.

- Run mvn install for the root module 
- Run the Authorization Server from the `spring-authorization-server` module
    - IMPORTANT: Modify the `/etc/hosts` file and add the entry `127.0.0.1 auth-server` `127.0.0.1 client-server` `127.0.0.1 resource-server`
- Run the Resource Server from `resource-server` module
- Run the client from `client-server` module
- Go to `https://client-server:8081/books`
    - Enter the credentials `admin/password`
- The module uses the new OAuth stack with Java 11

## Generating key stores

- generate key: `keytool -genkeypair -alias auth-server -keyalg RSA -keysize 4096 -validity 3650 -dname "CN=auth-server" -keypass changeit -keystore keystore.p12 -storeType PKCS12 -storepass changeit`

- export cert: `keytool -export -keystore keystore.p12 -alias auth-server -file auth.crt`

- import tryst store: `keytool -importcert -alias auth-server -file auth.crt -keystore truststore.p12`