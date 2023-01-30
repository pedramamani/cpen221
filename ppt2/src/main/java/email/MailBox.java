package email;

import java.util.*;

/**
 * A datatype that represents a mailbox or collection of email.
 */
public class MailBox {

    Set<Email> emails = new HashSet<>();

    /**
     * Add a new message to the mailbox
     *
     * @param msg the message to add
     * @return true if the message was added to the mailbox,
     * and false if it was not added to the mailbox (because a duplicate exists
     * or msg was null)
     */
    public boolean addMsg(Email msg) {
        try {
            Email newEmail = msg.clone();
            return emails.add(newEmail);
        } catch (Exception e) {
        }
        return false;
    }

    /**
     * Delete a message from the mailbox
     *
     * @param msg the message to delete
     * @return true if the message existed in the mailbox and it was removed,
     * else return false
     */
    public boolean delMsg(Email msg) {
        return emails.remove(msg);
    }

    /**
     * Obtain a list of messages in the mailbox, sorted by timestamp,
     * with most recent message first
     *
     * @return a list that represents a view of the mailbox with messages sorted
     * by timestamp, with most recent message first. If multiple messages have
     * the same timestamp, the ordering among those messages is arbitary.
     */
    public List<Email> getTimestampView() {
        List<Email> sortedEmails = new ArrayList<>(emails);
        Collections.sort(sortedEmails, TimestampComparator.DESCENDING);

        return sortedEmails;
    }

    /**
     * Obtain a list of messages, organized by message threads.
     *
     * The message thread view organizes messages by starting with the thread
     * that has the most recent activity (based on timestamps of messages in the
     * thread) first, and within a thread more recent messages appear first.
     * If multiple emails within a thread have the same timestamp then the
     * ordering among those messages is arbitary. Similarly, if more than one
     * thread can be considered "most recent", those threads can be ordered
     * arbitrarily.
     *
     * A thread is identified by using information in an email that indicates
     * whether an email was in response to another email. The group of emails
     * that can be traced back to a common parent email message form a thread.
     *
     * @return a list that represents a thread-based view of the mailbox.
     */
    public List<Email> getThreadedView() {
        List<List<Email>> threads = new ArrayList<>();
        List<Email> retList = new ArrayList<>();
        boolean makeThread;
        List addThread;

        List<Email> sortedEmails = new ArrayList<>(emails);
        Collections.sort(sortedEmails, TimestampComparator.ASCENDING);


        for (Email email : sortedEmails) {
            makeThread = true;
            addThread = null;
            for (List<Email> thread : threads) {
                for (Email existingEmail : thread) {
                    if (areRelated(existingEmail, email)) {
                        addThread = thread;
                        makeThread = false;
                    }
                }

                if (!makeThread) {
                    break;
                }
            }

            if (makeThread) {
                threads.add(new ArrayList<>(Arrays.asList(email)));
            } else {
                addThread.add(email);
            }
        }

        for (List<Email> thread : threads) {
            Collections.sort(thread, TimestampComparator.DESCENDING);
        }

        Collections.sort(threads, TimestampComparator.LIST_DESCENDING);

        for (List<Email> thread : threads) {
            for (Email email : thread) {
                try {
                    Email newEmail = email.clone();
                    retList.add(newEmail);
                }
                catch (Exception e) {
                }
            }
        }

        for (Email email : retList) {
            System.out.println(email.getMsgId());
        }

        return retList;
    }

    private boolean areRelated(Email email1, Email email2) {
        if (email1.getResponseTo().equals( email2.getMsgId()) ||
                email2.getResponseTo().equals( email1.getMsgId())) {
            return true;
        }
        return false;
    }
}
