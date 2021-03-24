package controller.member;

import javax.servlet.http.HttpSession;

public class MemberSessionUtils {
    public static final String Member_SESSION_KEY = "memberId";

    /* ���� 濡�洹몄�명�� �ъ�⑹���� ID瑜� 援ы�� */
    public static String getLoginmemberId(HttpSession session) {
        String memberId = (String)session.getAttribute(Member_SESSION_KEY);
        return memberId;
    }

    /* 濡�洹몄�명�� �����몄�瑜� 寃��� */
    public static boolean hasLogined(HttpSession session) {
        if (getLoginmemberId(session) != null) {
            return true;
        }
        return false;
    }

    /* ���� 濡�洹몄�명�� �ъ�⑹���� ID媛� memberId�몄� 寃��� */
    public static boolean isLoginmember(String memberId, HttpSession session) {
        String loginMember = getLoginmemberId(session);
        if (loginMember == null) {
            return false;
        }
        return loginMember.equals(memberId);
    }
}
