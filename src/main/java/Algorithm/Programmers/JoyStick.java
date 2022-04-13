package Algorithm.Programmers;

public class JoyStick {
    public static void main(String[] args) {
        System.out.println(new JoyStick().solution("JEROEN"));
        System.out.println(new JoyStick().solution("JAN"));
        System.out.println(new JoyStick().solution("JAZ"));
        System.out.println(new JoyStick().solution("AAAA"));
        System.out.println(new JoyStick().solution("ABAAB"));
        System.out.println(new JoyStick().solution("B"));
        System.out.println(new JoyStick().solution("Z"));
        System.out.println(new JoyStick().solution("BAAAABB"));
    }

    public int solution(String name) {
        int answer = 0;

        int aCount = 0;
        int currentPosition = 0;
        char[] chars = name.toCharArray();
        for(char c : chars) {
            if(c == 'A') {
                aCount++;
            }
        }

        if(chars[0] != 'A') {
            answer += Math.min(chars[0] - 'A', 'Z' - chars[0] + 1);
            chars[0] = 'A';
            aCount++;
        }

        while(aCount != name.length()) {
            int leftIndex = currentPosition;
            int rightIndex = currentPosition;
            while(chars[leftIndex] == 'A') {
                leftIndex--;
                if(leftIndex < 0) {
                    leftIndex = name.length() - 1;
                }
            }
            while(chars[rightIndex] == 'A') {
                rightIndex++;
                if(rightIndex >= name.length()) {
                    rightIndex = 0;
                }
            }
            int leftValue = (currentPosition + name.length() - leftIndex) % name.length() +
                    Math.min(chars[leftIndex] - 'A', 'Z' - chars[leftIndex] + 1);
            int rightValue = (rightIndex + name.length() - currentPosition) % name.length() +
                    Math.min(chars[rightIndex] - 'A', 'Z' - chars[rightIndex] + 1);
            if(leftValue <= rightValue) {
                chars[leftIndex] = 'A';
                currentPosition = leftIndex;
                answer += leftValue;
            }
            else {
                chars[rightIndex] = 'A';
                currentPosition = rightIndex;
                answer += rightValue;
            }
            aCount++;
        }

        return answer;
    }
}
