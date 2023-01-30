package email;

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SecondaryTests {

    private static Email thread1_0, thread1_1, thread1_2, thread1_3, thread1_4,
            thread2_0, thread2_1, thread2_2;

    private static final String user1 = "Student 1";
    private static final String user2 = "Student 2";
    private static final String user3 = "Mom 1";
    private static final String subject = "default subject";
    private static final String body = "default message body";

    @BeforeClass
    public static void setup() {

        thread1_0 = new Email(1, user1, user2, "How was the PPT?", "OK?");
        UUID tracker1 = thread1_0.getMsgId();

        thread2_0 = new Email(2, user3, user2, "Dinner", "Should I order from JamJar?");
        UUID tracker2 = thread2_0.getMsgId();

        thread1_1 = new Email(3, user2, user1, "Re How was the PPT?", "I hate Sathish!", tracker1);
        tracker1 = thread1_1.getMsgId();

        thread2_1 = new Email(4, user2, user3, "Re Dinner", "Yes, please!", tracker2);
        tracker2 = thread2_1.getMsgId();

        thread1_2 = new Email(5, user1, user2, "Re Re How was the PPT?", "Oh well. Dinner?", tracker1);
        tracker1 = thread1_2.getMsgId();

        thread1_3 = new Email(6, user2, user1, "Re Re Re How was the PPT?", "Can't. Meeting my mom for dinner.", tracker1);
        tracker1 = thread1_3.getMsgId();

        thread1_4 = new Email(7, user2, user1, "Was: How was the PPT?", "Let me see if you can join us.", tracker1);

        thread2_2 = new Email(8, user2, user3, "Re Re Dinner", "Can a friend join us for dinner?", tracker2);

    }


    private void setupMailBox(MailBox inbox) {
        inbox.addMsg(thread2_2);
        inbox.addMsg(thread2_1);
        inbox.addMsg(thread1_2);
        inbox.addMsg(thread1_3);
        inbox.addMsg(thread2_1);
        inbox.addMsg(thread1_1);
        inbox.addMsg(thread2_0);
        inbox.addMsg(thread1_0);
        inbox.addMsg(thread1_4);
    }

    @Test
    public void test1() {
        MailBox inbox = new MailBox();
        inbox.addMsg(thread1_0);
        List<Email> exp = Arrays.asList(thread1_0);
        assertEquals(exp, inbox.getThreadedView());
    }

    @Test
    public void test2() {
        MailBox inbox = new MailBox();
        setupMailBox(inbox);

        List<Email> exp = Arrays.asList(thread2_2, thread2_1, thread2_0, thread1_4, thread1_3, thread1_2, thread1_1, thread1_0);
        assertEquals(exp, inbox.getThreadedView());
    }

    @Test
    public void test3() {
        MailBox inbox = new MailBox();
        setupMailBox(inbox);

        inbox.delMsg(thread2_2);
        List<Email> exp = Arrays.asList(thread1_4, thread1_3, thread1_2, thread1_1, thread1_0, thread2_1, thread2_0);
        assertEquals(exp, inbox.getThreadedView());
    }
    
    @Test
    public void test4() {
        MailBox inbox = new MailBox();
        Email m1 = new Email(1, user1, user2, subject, body);
        Email n2 = new Email(2, user1, user2, subject, body);
        inbox.addMsg(m1);
        inbox.addMsg(n2);

        List<Email> exp = Arrays.asList(n2, m1);
        assertEquals(exp, inbox.getThreadedView());
    }

    @Test
    public void test5() {
        MailBox inbox = new MailBox();
        Email m1 = new Email(1, user1, user2, subject, body);
        Email n2 = new Email(2, user1, user2, subject, body);
        Email m5 = new Email(5, user2, user1, subject, body, m1.getMsgId());
        Email m6 = new Email(6, user2, user1, subject, body, m5.getMsgId());
        Email o9 = new Email(9, user2, user1, subject, body);
        inbox.addMsg(m1);
        inbox.addMsg(n2);
        inbox.addMsg(m5);
        inbox.addMsg(m6);
        inbox.addMsg(o9);

        List<Email> exp = Arrays.asList(o9, m6, m5, m1, n2);
        assertEquals(exp, inbox.getThreadedView());
    }

    @Test
    public void test6() {
        MailBox inbox = new MailBox();
        Email m1 = new Email(1, user1, user2, subject, body);
        Email n2 = new Email(2, user1, user2, subject, body);
        Email m5 = new Email(5, user2, user1, subject, body, m1.getMsgId());
        Email m6 = new Email(6, user2, user1, subject, body, m1.getMsgId());
        Email o9 = new Email(9, user2, user1, subject, body);
        inbox.addMsg(n2);
        inbox.addMsg(m5);
        inbox.addMsg(m6);
        inbox.addMsg(o9);

        List<Email> exp = Arrays.asList(o9, m6, m5, n2);
        assertEquals(exp, inbox.getThreadedView());
    }

    @Test
    public void test7() {
        MailBox inbox = new MailBox();
        Email m1 = new Email(1, user1, user2, subject, body);
        Email n2 = new Email(2, user1, user2, subject, body);
        Email m3 = new Email(5, user2, user1, subject, body, m1.getMsgId());
        Email m4 = new Email(6, user2, user1, subject, body, m1.getMsgId());
        Email o7 = new Email(7, user2, user1, subject, body);
        Email m8 = new Email(8, user2, user1, subject, body, m3.getMsgId());
        Email m9 = new Email(9, user2, user1, subject, body, m4.getMsgId());
        inbox.addMsg(m1);
        inbox.addMsg(n2);
        inbox.addMsg(m3);
        inbox.addMsg(m4);
        inbox.addMsg(o7);
        inbox.addMsg(m8);
        inbox.addMsg(m9);

        List<Email> exp = Arrays.asList(m9, m8, m4, m3, m1, o7, n2);
        assertEquals(exp, inbox.getThreadedView());
    }

    @Test
    public void test8() {
        MailBox inbox = new MailBox();
        Email m1 = new Email(1, user1, user2, subject, body);
        Email n2 = new Email(2, user1, user2, subject, body);
        inbox.addMsg(m1);
        inbox.addMsg(n2);

        List<Email> exp = Arrays.asList(n2, m1);
        List<Email> act = inbox.getThreadedView();
        Email n3 = new Email(3, user1, user2, subject, body);
        act.add(n3);
        assertEquals(exp, inbox.getThreadedView());
    }

    @Test
    public void test9() {
        MailBox inbox = new MailBox();
        Email m1 = new Email(1, user1, user2, subject, body);
        Email n2 = new Email(2, user1, user2, subject, body);
        Email m3 = new Email(5, user2, user1, subject, body, m1.getMsgId());
        Email m4 = new Email(6, user2, user1, subject, body, m1.getMsgId());
        Email o7 = new Email(7, user2, user1, subject, body);
        Email m8 = new Email(8, user2, user1, subject, body, m3.getMsgId());
        Email m9 = new Email(9, user2, user1, subject, body, m4.getMsgId());
        inbox.addMsg(n2);
        inbox.addMsg(m4);
        inbox.addMsg(m8);
        inbox.addMsg(m9);
        inbox.addMsg(o7);
        inbox.delMsg(m3);

        List<Email> exp = Arrays.asList(m9, m8, m4, m3, o7, n2);
        assertEquals(exp, inbox.getThreadedView());
    }

}
