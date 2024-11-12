# PARED_GAMES_aijyakae
Aijyakae는 Stable Diffusion사의 이미지 생성형 AI API를 활용하여 사용자가 원하는 프롬프트를 입력받아 이미지를 생성해주는 애플리케이션 입니다. JetPack Compose를 이용하여 선언형 UI로 코드를 작성했으며, 컴포넌트를 재활용하여 코드의 수를 줄이고,
리컴포지션을 적극적으로 활용하여 사용자의 편의를 개선하였습니다.

## 프로젝트 중점 사항
* MVVM 디자인 패턴을 이용하여 모델과 뷰의 의존성을 약화
* Preview 기능을 적극적으로 활용하여 시나리오 테스트 비용 절감
* FakeData를 이용하여 백엔드랑 독립적으로 UI Test
* Retrofit2를 활용하여 백엔드 API와 Stable Diffusion API를 호출
* AI Model의 경우에는 API Response와 Image Response간의 시간차가 발생, 이 부분을 retry 전략을 활용하여 해결
* SharedPreference와 Billing API를 이용하여 단기결제,정기결제 기능 구현
* Local Properties를 활용하여 

## 관점

### 사용자
* 사용자는 처음 앱에 접속했을 때, 원하는 캐릭터의 특징을 버튼으로 눌러, 입력값에 따라 자동 생성된 프롬프트를 기반으로 첫 이미지를 생성
* 이후 사용자는 첫 이미지에 사용된 프롬프트를 기반으로, 어떻게 프롬프트를 작성해야 할지 감을 잡고 이미지를 생성 가능
* 이 경우 해상도와 그림체 (Traning Model)을 선택하여 원하는 그림을 뽑아낼 수 있음.

### 서버
* Stable Diffusion API 서버는 프롬프트와 model, resoultion 등의 정보를 인자로 넣어 이미지를 생성
* Backend 서버는 사용자가 사용한 이미지, 프롬프트, 고유 ID를 서버로 전송하여 DB에 저장
* Backend 서버는 사용자가 게시판에 들어왔을 때, 유저의 사진, 프롬프트, 고유 ID를 리스트업하여 20개의 게시글을 보여줌

### 게시판
* 게시판에서는 사용자의 이미지, 프롬프트, 고유 Id를 볼 수 있음
* 게시판의 이미지를 클릭하면 이미지 저장과 업로더가 사용한 프롬프트를 사용자의 textarea에 복사할 수 있음

## 화면 소개

### 시작 화면
![1](https://github.com/user-attachments/assets/a3a44471-b426-4281-8849-863b957ff754)

### 캐릭터 선택 화면
![2](https://github.com/user-attachments/assets/90d09e7f-8419-49e4-820c-d7835c6e0b19)

### 첫 이미지 생성 화면
![3](https://github.com/user-attachments/assets/4cfdb3f9-d1fe-4c4b-97b1-22dc87827629)

### 프롬프트 기반 이미지 생성 화면
![4](https://github.com/user-attachments/assets/5f338cbd-a47c-4ee2-a6d3-c0547c131cf7)

### 완성된 이미지 뷰어 화면
![5](https://github.com/user-attachments/assets/8c92f1d0-358d-40ed-8292-69e65c0caf4e)

### 그림 게시판 화면
![6](https://github.com/user-attachments/assets/e1a2b478-c236-43a1-aa5f-eaa909c5bcae)

## Server Repository

https://github.com/lectykim/PARED_GAMES_aijyakae_server
