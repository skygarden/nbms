# NBMS Backend - 개발 환경 설정 가이드

## 프로젝트 개요

NBMS(Next Blue Management System) Backend는 기업 운영 관리를 위한 Spring Boot REST API 서버입니다.

**프로젝트 경로**: `C:\Dev\new_nbms_workspace\new-nbms\nbms` (이 간소)

## 기술 스택

| 항목 | 버전 | 설명 |
|------|------|------|
| Spring Boot | 4.1.0 | 웹 프레임워크 |
| Java | 21 | 프로그래밍 언어 |
| Gradle | 8.x | 빌드 도구 |
| MyBatis | 3.0.4 | ORM/SQL 매퍼 |
| Spring Security | 최신 | 인증/인가 |
| JWT (JJWT) | 0.12.6 | 토큰 기반 인증 |
| MySQL | 8.4.0 | 개발/테스트/운영 DB |
| Lombok | - | 보일러플레이트 제거 |

## 빠른 시작

### 1. 환경변수 설정
프로젝트 루트에 `.env` 파일 생성:
```env
DB_URL=jdbc:mysql://localhost:3306/nbms_dev
DB_USER=dbadm
DB_PASSWORD=dbadm#123
SERVER_PORT=8080
SERVER_SERVLET_CONTEXT_PATH=/api
```

### 2. 빌드 및 테스트
```bash
cd nbms
./gradlew clean build
```

### 3. 개발 서버 시작
```bash
./gradlew bootRun
```
접속: http://localhost:8080/api

## 프로젝트 구조

```
nbms/
├── src/
│   ├── main/
│   │   ├── java/kr/co/nexblue/nbms/
│   │   │   ├── NbmsApplication.java
│   │   │   ├── config/          # Spring 설정
│   │   │   ├── controller/      # REST API 엔드포인트
│   │   │   ├── service/         # 비즈니스 로직
│   │   │   ├── domain/          # Entity, DTO
│   │   │   ├── mapper/          # MyBatis 인터페이스
│   │   │   ├── security/        # JWT, 인증/인가
│   │   │   └── common/          # 공통 유틸, 상수
│   │   └── resources/
│   │       ├── application.properties
│   │       ├── mapper/          # MyBatis XML
│   │       └── sql/             # DB 초기화
│   └── test/
│       └── resources/application.properties
├── build.gradle
├── .env                 # 환경변수 (git 제외)
└── .claude/            # Claude 개발 가이드
```

## 주요 명령어

```bash
# 빌드
./gradlew clean build

# 실행
./gradlew bootRun

# 테스트
./gradlew test

# 특정 테스트
./gradlew test --tests "TestName"

# 린트
./gradlew checkstyle

# 포매팅
./gradlew spotlessApply

# 의존성 확인
./gradlew dependencies | grep -i "mybatis"
```

## 하네스 구조

### 프로젝트 루트 설정
- ✅ `CLAUDE.md` - 개발 표준 및 규칙
- ✅ `.prompt.md` - 개발 프롬프트
- ✅ `settings.json` - Claude 설정

### 공용 하네스 (01.Docs/.claude/)
- **Rules**: `backend-rules.md`
- **Skills**: `backend-api-development/`, `code-review-backend/`, `database-migration/`

## 환경 설정 상세

### .env 파일 (프로젝트 루트)
```env
# Database Configuration
DB_URL=jdbc:mysql://localhost:3306/nbms_dev
DB_USER=dbadm
DB_PASSWORD=dbadm#123

# Server Configuration
SERVER_PORT=8080
SERVER_SERVLET_CONTEXT_PATH=/api

# MyBatis Configuration
MYBATIS_MAPPER_LOCATIONS=classpath:mapper/*.xml
MYBATIS_TYPE_ALIASES_PACKAGE=kr.co.nexblue.nbms
```

### application.properties
환경변수를 참조하여 자동 로드됨 (NbmsApplication.java)

### application-test.properties
테스트 환경에서 MySQL 8.4.0 사용

## API 개발 프로세스

1. **DB 설계** → SQL 작성
2. **Entity/DTO 정의** → Lombok 활용
3. **MyBatis Mapper** → SQL 매핑 (XML)
4. **Service** → 비즈니스 로직
5. **Controller** → REST 엔드포인트
6. **테스트** → JUnit 5 + Mockito
7. **문서** → API 명세

## 보안 체크리스트

- ✅ .env 파일 .gitignore 등록
- ✅ 모든 입력값 검증
- ✅ SQL 인젝션 방지 (MyBatis 파라미터)
- ✅ 비밀번호 해싱 (BCrypt)
- ✅ JWT 토큰 검증
- ✅ 민감 정보 로그 금지

## 문제 해결

### 빌드 실패
```bash
./gradlew clean build --refresh-dependencies
```

### MySQL 연결 오류
- .env 파일 존재 확인
- MySQL 서비스 실행 확인
- 포트 3306 확인

### 테스트 실패
- application-test.properties 확인
- src/test/resources/ 디렉토리 확인
- MySQL 테스트 DB 연결 확인

## 더 알아보기

- `.claude/CLAUDE.md` - 자세한 개발 표준
- `.claude/.prompt.md` - 개발 프롬프트
- `../01.Docs/.claude/CLAUDE.md` - 공용 프로젝트 규칙

- `../01.Docs/.claude/skills/database-migration/SKILL.md`

#### Rules
- `../01.Docs/.claude/rules/backend-rules.md`
- `../01.Docs/.claude/rules/frontend-rules.md`

#### 추가 문서
- `../01.Docs/.claude/README.md` - 하네스 사용 가이드

## 🎯 Claude에게 경로 명시하는 방법

Claude 세션에서 다음과 같이 말해주세요:

```
01.Docs 폴더의 .claude 설정을 사용해줄래?
경로: ../01.Docs/.claude/

혹은 특정 작업 시:
/backend-api-development (이 Skill은 ../01.Docs/.claude/skills/backend-api-development/SKILL.md에 있습니다)
```

## 📋 주요 설정

### Permissions (권한)
- Read: 자유 허용
- Write/Edit/Bash: 승인 필요
- Delete: 전면 금지

### Hooks (자동 실행)
- git/gradle 명령 전 확인
- 파일 수정 후 자동 포매팅
- 설정 파일 수정 시 경고

### Slash Commands
- `/build` - 빌드 및 테스트
- `/test` - 테스트 실행
- `/review` - 코드 리뷰
- `/api-create` - API 생성
- `/component-create` - 컴포넌트 생성
- `/db-migrate` - DB 마이그레이션

## 🚀 사용 방법

### 1단계: 기본 규칙 확인
```
사용자: "CLAUDE.md를 읽고 프로젝트 규칙을 설명해줄래?"
Claude: CLAUDE.md를 로드하여 설명
```

### 2단계: Skill 활성화
```
사용자: "새로운 API 엔드포인트를 만들어줄래?"
Claude: backend-api-development Skill 활성화
         (경로: ../01.Docs/.claude/skills/backend-api-development/SKILL.md)
```

### 3단계: 명시적 경로 사용
```
사용자: "../01.Docs/.claude/skills/backend-api-development/SKILL.md를 참고해서 API를 만들어줄래?"
Claude: 명시적 경로를 통해 Skill 로드
```

## 💡 팁

- **Skills와 Rules는 01.Docs에 집중화**: 여러 프로젝트에서 공유 가능
- **프로젝트 루트는 경량화**: CLAUDE.md와 settings.json만 유지
- **명시적 경로**: 필요시 상대 경로로 01.Docs 리소스 참조

## 📞 문제 해결

### Claude가 Skills를 찾지 못할 때
→ 명시적 경로 사용: `../01.Docs/.claude/skills/skill-name/SKILL.md`

### Settings가 적용되지 않을 때
→ `.claude/settings.json` 존재 확인
→ 새로운 Claude 세션 시작

### 심링크가 작동하지 않을 때
→ 권한 문제: 관리자 권한 필요 또는 다른 방법 사용
→ 현재 구조: 상대 경로로 01.Docs 참조

---

**Status**: ✅ 활성 설정  
**Last Update**: 2025-08-17
