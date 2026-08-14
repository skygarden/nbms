# CLAUDE.md - NBMS 프로젝트 개발 표준

## 프로젝트 설명

NBMS(Next Blue Management System)는 기업 운영 관리를 위한 통합 시스템입니다.
- **Backend**: Spring Boot 3.x + Gradle + MyBatis + JWT
- **Frontend**: Vite + Vue 3 (또는 React) + TypeScript
- **Database**: PostgreSQL (운영환경) / H2 (개발환경)
- **배포**: Docker + Kubernetes

## 대화 방식

- **불필요한 인사말 제거**: "좋은 질문입니다" 같은 인사말은 생략하고 바로 답하기
- **확실성 표시**: 날짜, 수치, 출처가 불확실하면 반드시 먼저 불확실하다고 명시하기
- **길이 조절**: 간단한 질문은 2-3줄, 복잡한 설계는 충분히 자세히
- **코드 vs 설명**: 코드 작업은 구체적으로, 아키텍처는 먼저 선택지 제시하기

## 변경 통제

- **큰 변경 전 확인**: 파일 전체 재작성, 구조 변경, 파일 삭제는 먼저 무엇을 바꿀지 설명하고 승인받기
- **요청 범위 준수**: 한 메서드만 수정하면 되는데 전체 클래스를 리팩토링하지 않기
- **변경 요약**: 작업 후 어떤 파일을 고쳤고, 각 파일에서 무엇을 바꿨는지 짧게 정리하기
- **외부 영향 행동 차단**: 배포, DB 마이그레이션, 외부 API 호출은 명시적 승인 필요

## 기술 스택 (고정)

### Backend
- **Language**: Java 17+
- **Framework**: Spring Boot 3.x
- **Build**: Gradle 8.x
- **ORM**: MyBatis (with MyBatis-Plus 선택사항)
- **Security**: Spring Security + JWT (jjwt 라이브러리)
- **Database**: PostgreSQL 14+
- **Logging**: SLF4J + Logback
- **Testing**: JUnit 5 + Mockito

### Frontend
- **Framework**: Vue 3 (또는 프로젝트 선택)
- **Build Tool**: Vite 5.x
- **Language**: TypeScript 5.x
- **Package Manager**: npm
- **Linting**: ESLint
- **Formatting**: Prettier
- **Testing**: Vitest (선택사항)

## 개발 규칙 (Karpathy Rules 준용)

### 1. 코딩 전 먼저 생각하기
- 비즈니스 로직이 불명확하면 멈추고 질문하기
- 아키텍처 패턴을 정하지 않고 코딩하지 않기
- 데이터베이스 스키마 변경은 설계 검토 후 진행하기

### 2. 단순성 최우선
- 요청받지 않은 최적화, 추상화 레이어, 유틸리티 함수 추가하지 않기
- 한 클래스 / 함수는 하나의 책임만 갖기
- 200줄 코드가 50줄로 가능한가 생각해보기

### 3. 외과 수술식 수정
- 요청받은 부분만 정확히 수정하기
- 관련 없는 코드, 주석, 포매팅 건드리지 않기
- 당신의 변경이 만든 orphan만 정리하기 (기존 데드 코드는 제거 금지)

### 4. 목표 중심 실행
- 변경하기 전에 성공 기준 정하기
- 테스트 작성 → 통과시키기 순서 준수
- 기존 테스트, 린트, 빌드가 깨지지 않는지 확인

## 자주 쓰는 명령어

### Backend
```bash
# 빌드
./gradlew clean build

# 테스트
./gradlew test

# 린트 & 포매팅
./gradlew checkstyle
./gradlew spotlessApply

# 로컬 실행
./gradlew bootRun

# 의존성 확인
./gradlew dependencies
```

### Frontend
```bash
# 개발 서버
npm run dev

# 빌드
npm run build

# 린트 & 포매팅
npm run lint
npm run format

# 테스트
npm run test
```

### Database
```bash
# 마이그레이션 실행
./gradlew flywayMigrate

# 롤백 (신중하게!)
./gradlew flywayUndo
```

## 파일 수정 원칙

### Backend 코드
- **DTO/Entity 변경**: MyBatis 매퍼 파일도 함께 업데이트 필수
- **API 엔드포인트 추가**: 문서(docs/api.md) 업데이트 필수
- **권한/인증 로직**: 모든 변경 전에 기존 테스트 실행 필수
- **데이터베이스 연결**: 테스트 데이터베이스(H2)에서 먼저 검증

### Frontend 코드
- **컴포넌트 추가**: 스토리북 등재 (있는 경우) 또는 문서 추가
- **API 호출 변경**: 백엔드 엔드포인트 버전 확인 후 진행
- **상태 관리 변경**: 기존 테스트 스크린샷 확인
- **타입스크립트**: 타입 오류 제거 후 커밋

## 기억과 연속성

### 중요 결정 기록
- 새 기능 방향, 기술 선택, 마이그레이션 전략을 MEMORY.md에 남기기
- 왜 그렇게 결정했는지 함께 기록하기

### 세션 종료 시
- 완료한 일, 진행 중인 일, 다음에 이어야 할 일 요약
- 막힌 부분, 재검토가 필요한 부분 기록
- ERRORS.md에 학습한 실패 패턴 남기기

## 보안 가이드

- **민감한 파일 보호**: .env, application-secrets.yml, 개인키 파일은 절대 건드리지 않기
- **로그에 민감 정보 남기지 않기**: 비밀번호, API 키, 토큰 기록 금지
- **입력 검증**: 모든 API 요청은 Bean Validation 또는 커스텀 검증 필수
- **SQL 인젝션 방지**: PreparedStatement 또는 MyBatis 파라미터 바인딩 필수

## 고위험 행동 차단

다음 행동은 현재 세션의 명시적 승인 필수:

1. **배포 관련**: `./gradlew bootJar`, docker build & push
2. **데이터베이스**: 마이그레이션, 테이블 삭제, 데이터 수정 쿼리
3. **기존 API 변경**: 엔드포인트 경로 변경, 요청/응답 필드 제거
4. **의존성 업그레이드**: 메이저 버전 업그레이드
5. **외부 전송**: Git push, PR merge, 문서 공개

## 팀 정보

- **Backend Team**: Spring Boot 기반 API 개발
- **Frontend Team**: Vue/React 기반 UI 개발
- **DevOps**: Docker, Kubernetes 배포
- **QA**: 통합 테스트, 성능 테스트

모르는 것이 있으면 팀에 먼저 물어보기!
