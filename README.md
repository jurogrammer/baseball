# baseball
클린하게~ 야구게임을 만들어보자

# 구조
![architecture image](https://github.com/jurogrammer/baseball/blob/master/images/baseball_architecture.drawio.png)

# Idea

## 사용자의 입출력 방식은 Detail이다!
소켓 통신을 통해서 통신할 수도 있고, 

단순히 터미널을 통해서도 통신할 수 있다.

따라서,
1. 입출력 방식을 추상화
2. 각 방식에 맞는 입출력 메세지들을 추상화

한다.

## 의존성 주입 및 플로우 컨트롤 해주는 클래스를 작성하라.
GameApp이 담당한다.

## 테스트 가능한 단위로 분리하라.
로직이 복잡해진다 -> 테스트하고 싶다.

로직이 복잡해진다 -> 따로 분리하여 클래스로 분리해도 될 로직이다.


클래스 분리의 이점: 복잡한 로직 수정시, 캡슐화로 인해 영향의 범위를 복잡한 로직으로 한정지을 수 있다.
