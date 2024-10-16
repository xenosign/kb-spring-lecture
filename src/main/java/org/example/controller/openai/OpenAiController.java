package org.example.controller.openai;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/openai")
public class OpenAiController {

    // OpenAI API 엔드포인트
    private static final String API_URL = "https://api.openai.com/v1/chat/completions";

    // OpenAI API 키 (발급받은 API 키로 대체)
    private static final String API_KEY = "";

    // RestTemplate 객체를 사용하여 OpenAI API 호출
    private final RestTemplate restTemplate = new RestTemplate();

    @GetMapping("/chat")
    public ResponseEntity<String> callOpenAiApi() {
        // HTTP 헤더 설정 (UTF-8 인코딩 적용)
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
        headers.set("Authorization", "Bearer " + API_KEY);

        // 임의의 데이터 설정
        String monthlyIncome = "5000000";  // 월 소득: 500만 원
        String monthExpenses = "2000000";  // 월 지출: 200만 원
        String totalSavings = "100000000"; // 저축 금액: 1억 원
        String totalLoan = "300000000";    // 부채 금액: 3억 원
        String totalInvestment = "150000000"; // 총 투자 금액: 1억 5천만 원
        String totalAssets = "600000000";  // 총 자산: 6억 원

        // 사용자의 자산 현황 메시지 생성
        String content = "사용자의 자산 현황은 다음과 같습니다: " +
                "1. 사용자 월 소득: " + monthlyIncome + "원, " +
                "2. 사용자 월 지출: " + monthExpenses + "원, " +
                "3. 사용자 저축 금액: " + totalSavings + "원 (저축 상품: KB내맘대로적금, 저축 시작일: 2023년 1월 1일, 저축 만기일: 2028년 1월 1일, 저축 이자율: 2.5%), " +
                "4. 사용자 부채 금액: " + totalLoan + "원 (대출 종류: 주택 담보 대출, 대출 이자율: 3.5%, 대출 만기일: 2033-01-01), " +
                "5. 사용자 총 투자 평가 금액: " + totalInvestment + "원, " +
                "6. 사용자 총 연금 적립 금액: 1500000000원, " +
                "7. 사용자 총 자산: " + totalAssets + "원. " +
                "주어진 데이터를 기반으로 사용자의 재무 상황을 분석하고, JSON 형식으로 다음과 같은 전략을 제공해 주세요: {" +
                "\"사용자_성향_분석\": {" +
                "   \"종합_투자_성향(#키워드 나열)\": \"\"," +
                "   \"권장_자산_배분\": {" +
                "       \"현금성_자산\": 0," +
                "       \"안전자산\": 0," +
                "       \"위험자산\": 0" +
                "   }" +
                "}," +
                "\"개선된_전략_요약\": {" +
                "   \"지출_조정\": \"월 지출을 ?% (감소/유지/증가)하여 {개선 방안}을 고려해야 합니다.\"," +
                "   \"저축_전략\": {" +
                "       \"목표_저축률\": 0," +
                "       \"권장_저축_상품\": []" +
                "   }," +
                "   \"투자_전략\": {" +
                "       \"목표_투자_비율\": 0," +
                "       \"권장_포트폴리오\": {" +
                "           \"현금성_자산\": 0," +
                "           \"안전자산\": 0," +
                "           \"위험자산\": 0" +
                "       }," +
                "       \"권장_투자_상품\": []" +
                "   }," +
                "   \"부채_관리\": {" +
                "       \"우선순위\": \"\"," +
                "       \"상환_계획\": \"\"," +
                "       \"금리_재조정\": \"\"," +
                "       \"추가_상환_전략\": \"\"" +
                "   }" +
                "}," +
                "\"주요_재무_지표\": {" +
                "   \"부채비율\": 0," +
                "   \"저축률\": 0," +
                "   \"투자수익률\": 0," +
                "   \"부채상환비율\": 0" +
                "}," +
                "\"결론_및_권고사항(#키워드)\": \"\"" +
                "}";

        // 요청 데이터 구성
        Map<String, Object> data = new HashMap<>();
        data.put("model", "gpt-3.5-turbo");

        // 메시지 배열 구성
        Map<String, String> systemMessage = new HashMap<>();
        systemMessage.put("role", "system");
        systemMessage.put("content", "You are a helpful assistant.");

        Map<String, String> userMessage = new HashMap<>();
        userMessage.put("role", "user");
        userMessage.put("content", content);  // 자산 현황 메시지 설정

        data.put("messages", new Map[] {systemMessage, userMessage});
        data.put("max_tokens", 1000);  // 최대 토큰 수 설정

        // 요청을 담은 HttpEntity 생성
        HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(data, headers);

        try {
            // POST 요청 보내기
            ResponseEntity<Map> response = restTemplate.exchange(
                    API_URL,
                    HttpMethod.POST,
                    requestEntity,
                    Map.class
            );

            // 응답 처리
            if (response.getStatusCode().is2xxSuccessful()) {
                Map<String, Object> responseBody = response.getBody();
                if (responseBody != null) {
                    // AI 응답 가져오기
                    Map<String, Object> choices = (Map<String, Object>) ((List<Object>) responseBody.get("choices")).get(0);
                    Map<String, String> message = (Map<String, String>) choices.get("message");
                    String contentResponse = message.get("content");

                    // UTF-8 인코딩을 적용하여 응답 반환
                    HttpHeaders responseHeaders = new HttpHeaders();
                    responseHeaders.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
                    return ResponseEntity.ok()
                            .headers(responseHeaders)
                            .body(contentResponse);  // UTF-8로 인코딩된 응답 본문
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error occurred while calling OpenAI API: " + e.getMessage());
        }

        return ResponseEntity.status(500).body("No response from OpenAI API.");
    }
}
