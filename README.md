# AI-Powered Release Notes

> Gerador de release notes com inteligência artificial. O sistema analisa commits e PRs de um repositório e gera changelogs legíveis automaticamente.

## Estrutura do Mono-repo

```
ai-powered-release-notes/
├── backend/                  # Spring Boot 4.1 · Java 21 · PostgreSQL
│   ├── src/
│   ├── mvnw / mvnw.cmd
│   └── pom.xml               # Herda do pom.xml raiz
│
├── frontend/                 # Angular 19 · Sass · Standalone Components
│   ├── src/
│   ├── angular.json
│   └── package.json
│
├── .github/                  # CI/CD workflows (a criar)
├── .gitignore
├── .env                      # Variáveis de ambiente (não versionado)
├── package.json              # Workspace npm — scripts unificados
├── pom.xml                   # POM pai Maven — aggregator
└── README.md
```

## Pré-requisitos

| Ferramenta | Versão mínima |
|---|---|
| Java | 21 |
| Maven | 3.9+ (ou use `./mvnw` no `backend/`) |
| Node.js | 18.x LTS |
| npm | 9.x |
| PostgreSQL | 14+ |

## Rodando localmente

### Backend (Spring Boot)

```bash
cd backend
./mvnw spring-boot:run
# API disponível em http://localhost:8080
# Swagger UI: http://localhost:8080/swagger-ui/index.html
```

### Frontend (Angular)

```bash
cd frontend
npm run start
# App disponível em http://localhost:4200
```

### Ambos simultaneamente (requer `npm-run-all`)

```bash
# Na raiz do projeto:
npm install          # instala npm-run-all na raiz
npm run dev          # inicia backend e frontend em paralelo
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
# Backend — gera o .jar em backend/target/
npm run backend:build

# Frontend — gera o bundle em frontend/dist/
npm run frontend:build
```
