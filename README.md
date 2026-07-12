# AI-Powered Release Notes

> Gerador de release notes com inteligência artificial. O sistema analisa commits e PRs de um repositório e gera changelogs legíveis automaticamente.

## Tecnologias
- **Java**
- **Spring Boot**
- **Spring Security**
- **PostgreSQL**
- **Liquibase** (Migration)
- **Swagger**

## Estrutura (Mono-Repo)

```
ai-powered-release-notes/
├── backend/                  
│   ├── src/
│   ├── mvnw / mvnw.cmd
│   └── pom.xml              
│
├── frontend/                 
│   ├── src/
│   ├── angular.json
│   └── package.json
│
├── .github/                  
├── .gitignore
├── .env                      
├── package.json              
├── pom.xml                  
└── README.md
```
## Rodando localmente

### Backend (Spring Boot)

```bash
cd backend
./mvnw spring-boot:run
# Swagger URL: http://localhost:8080/swagger-ui/index.html
```

### Frontend (Angular)

```bash
cd frontend
npm run start
# Angular URL: http://localhost:4200
```

### Ambos simultaneamente (requer `npm-run-all`)

```bash
npm install
npm run dev
```

## Variáveis de Ambiente

Crie um arquivo `.env` na raiz baseado no `.env.example` (a criar):

```env
# Database
SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5432/release_notes
SPRING_DATASOURCE_USERNAME=postgres
SPRING_DATASOURCE_PASSWORD=sua_senha

# AI Provider (ex: OpenAI)
AI_API_KEY=sk-...
```

## Build para Produção

```bash
npm run backend:build
npm run frontend:build
```

**_Desenvolvido por Lucas Storck_**
