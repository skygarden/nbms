# .claude - 프로젝트 하네스 설정

이 폴더는 Claude Code 하네스 설정의 프로젝트 루트 부분입니다.

## 📁 구조

```
프로젝트 루트 (.claude/)
├── CLAUDE.md              # 프로젝트 루트의 기본 규칙
├── settings.json          # 권한 및 Hooks 설정
├── README.md              # 이 파일
└── (프로젝트 루트에 위치)

공용 하네스 (01.Docs/.claude/)
├── CLAUDE.md
├── README.md
├── settings.json
├── skills/                # 반복 업무 매뉴얼
│   ├── backend-api-development/
│   ├── frontend-component-development/
│   ├── code-review-backend/
│   └── database-migration/
└── rules/                 # 경로별 세부 규칙
    ├── backend-rules.md
    └── frontend-rules.md
```

## 🔍 Claude가 사용할 경로

### 프로젝트 루트에서 로드되는 파일
- ✅ `CLAUDE.md` (프로젝트 루트)
- ✅ `.claude/settings.json` (프로젝트 루트의 .claude/)

### 공용 하네스 위치 (01.Docs)
모든 Skills와 Rules는 다음 위치에 있습니다:

**📍 기본 경로**: `../01.Docs/.claude/`

#### Skills
- `../01.Docs/.claude/skills/backend-api-development/SKILL.md`
- `../01.Docs/.claude/skills/frontend-component-development/SKILL.md`
- `../01.Docs/.claude/skills/code-review-backend/SKILL.md`
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
- git/docker/flyway 명령 전 확인
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
