# Spring 질문 게시판
## 개요
- SpringBoot를 활용한 질문 게시판

#### 만든 목적
- HTML, CSS, JavaScript, Java를 배운 내용을 바탕으로 SpringBoot를 활용하여 질문 게시판 구현
- Nav, 페이징, 게시물 번호 지정, 답변개수 표시, 스프링 시큐리티, 회원가입, 로그인, 수정 삭제, 추천버튼을 구현하기위한 목적

#### 일정
- 24.04.1 ~ 20.03.12
- 참여도 : 100% (개인 프로젝트)

## 사용 기술 및 개발 환경
- Language : HTML5, CSS3, JavaScript, Java, SpringBoot
- Tool : SpringB , GitHub, Git(SourceTree)

# 내용

## 구현 기능
### HTML, CSS
- Header, Nav, 표, footer
- 프론트 부분은 BootStrap을 사용하며 반응형 Nav 바를 구현하였다.

### 페이징 기능
- findAll 메서드를 이용하여 게시물이 많아질 경우 페이지 번호를 구현하기위해 페이징 기능을 구현하였다.
- 페이지 리스트를 보기 좋게 표시하기 위해 BootStrap의 pagination 컴포넌트 이용.
  
### 게시물 번호 지정, 답변 개수
#### 게시물 번호 지정
- 게시물 번호 = 전체 게시물 개수 - (현재 페이지 * 페이지당 게시물 개수) - 나열 인덱스
- 게시물 번호: 최종 표시될 게시물의 번호
- 전체 게시물 개수: 데이터베이스에 저장된 게시물 전체 개수
- 현재 페이지: 페이징에서 현재 선택한 페이지
- 페이지당 게시물 개수: 한 페이지당 보여줄 게시물 개수
- 나열 인덱스: for 문 안의 개시물 순서
  
~~~
       <tbody>
            <tr th:each="question, loop : ${paging}">
                <td th:text="${paging.getTotalElements - (paging.number * paging.size) - loop.index}"></td>
                <td>
                    <a th:href="@{|/question/detail/${question.id}|}" th:text="${question.subject}"></a>
                </td>
                <td th:text="${#temporals.format(question.createDate, 'yyyy-MM-dd HH:mm')}"></td>
            </tr>
        </tbody>

~~~

- paging.getTotalElements:	전체 게시물 개수를 의미한다.
- paging.number:	현재 페이지 번호를 의미한다.
- paging.size:	페이지당 게시물 개수를 의미한다.
- loop.index:	나열 인덱스를 의미한다(0부터 시작).

#### 답변 개수
- th:if="${#lists.size(question.answerList) > 0}": 답변이 있는지 조사
- th:text="${#lists.size(question.answerList)}": 답변 개수를 표시했다.
 
### 스프링 시큐리티(Spring Security)
- 회원가입과 로그인을 도와주는고 웹 애플리케이션의 인증과 권한을 담당하는 스프링 하위 프레임워크인 스프링 시큐리티 사용 

### 회원가입 기능
- 중복된 회원가입 방지를 오류를 500 오류 메시지 페이지에서 Try Catch문을 사용하여
  중복된 회원아이디가 있을 시 '이미 등록된 사용자입니다'라는 오류 메시지 화면에 표시
- 

~~~
       try {
            userService.create(userCreateForm.getUsername(), 
                    userCreateForm.getEmail(), userCreateForm.getPassword1());
        }catch(DataIntegrityViolationException e) {
            e.printStackTrace();
            bindingResult.reject("signupFailed", "이미 등록된 사용자입니다.");
            return "signup_form";
        }catch(Exception e) {
            e.printStackTrace();
            bindingResult.reject("signupFailed", e.getMessage());
            return "signup_form";
        }

~~~

![image](https://github.com/regdoll031/SpringBoot_board/assets/145822755/751352e6-6cd9-4820-a4bc-f2692d3d8fe7)

### 로그인과 로그아웃 기능

~~~
@RequiredArgsConstructor
@Service
public class UserSecurityService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<SiteUser> _siteUser = this.userRepository.findByusername(username);
        if (_siteUser.isEmpty()) {
            throw new UsernameNotFoundException("사용자를 찾을수 없습니다.");
        }
        SiteUser siteUser = _siteUser.get();
        List<GrantedAuthority> authorities = new ArrayList<>();
        if ("admin".equals(username)) {
            authorities.add(new SimpleGrantedAuthority(UserRole.ADMIN.getValue()));
        } else {
            authorities.add(new SimpleGrantedAuthority(UserRole.USER.getValue()));
        }
        return new User(siteUser.getUsername(), siteUser.getPassword(), authorities);
    }
}

~~~

- 스프링 시큐리티의 UserDetailsService는 loadUserByUsername 메서드를 구현하도록 강제하는 인터페이스이다.
- loadUserByUsername 메서드는 사용자명(username)으로 스프링 시큐리티의 사용자 객체를 조회하여 리턴하는 메서드

~~~

 <a class="nav-link" sec:authorize="isAnonymous()" th:href="@{/user/login}">로그인</a>
                    <a class="nav-link" sec:authorize="isAuthenticated()" th:href="@{/user/logout}">로그아웃</a>

~~~

- sec:authorize="isAnonymous()": 로그인되지 않은 경우에 해당 요소(로그인 링크) 표시
- sec:authorize="isAuthenticated()": 로그인된 경우에 해당 요소(로그아웃 링크) 표시

### 수정 / 삭제 / 추천 기능 
#### 수정
~~~

        <div class="my-3">
            <a th:href="@{|/question/modify/${question.id}|}" class="btn btn-sm btn-outline-secondary"
                sec:authorize="isAuthenticated()"
                th:if="${question.author != null and #authentication.getPrincipal().getUsername() == question.author.username}"
                th:text="수정"></a>
        </div>

~~~

- #authentication.getPrincipal().getUsername() == question.author.username을 사용하여 [수정]버튼이 로그인한 사용자와 글쓴이가 동일할 경우 노출

#### 삭제
~~~

        <div class="my-3">
            <a href="javascript:void(0);" th:data-uri="@{|/question/delete/${question.id}|}"
                class="delete btn btn-sm btn-outline-secondary" sec:authorize="isAuthenticated()"
                th:if="${question.author != null and #authentication.getPrincipal().getUsername() == question.author.username}"
                th:text="삭제"></a>
        </div>

~~~

- href 속성값을 javascript:void(0)로 설정하고 삭제를 실행할 URL을 얻기 위해 th:data-uri 속성을 추가한 뒤, [삭제] 버튼을 클릭하는 이벤트를 확인하기 위해 class 속성에 delete 항목을 추가했다.

#### 추천
~~~

        <div class="my-3">
           <a href="javascript:void(0);" class="recommend btn btn-sm btn-outline-secondary"
                th:data-uri="@{|/question/vote/${question.id}|}">
                추천
                <span class="badge rounded-pill bg-success" th:text="${#lists.size(question.voter)}"></span>
            </a>
        </div>

~~~

- class="recommend btn btn-sm btn-outline-secondary" 에서 recommend는 추천 버튼을 클릭하는 이벤트를 얻기 위한 클래스이다.
- 
# 결과
## HTML, CSS, JavaScript , Java, SpringBoot, BootStrap 사용
- BootStrap으로 nav바를 구성하였다.
- 회원가입 후 질문 등록과 추천
- 질문 등록 수정/삭제, 답변 등록 수정/삭제
