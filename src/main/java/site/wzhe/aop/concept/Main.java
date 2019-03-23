package site.wzhe.aop.concept;

public class Main {
    public static void main(String[] args) {
        /*
        aop run
         */
        ForumService forumService = new ForumService();
        forumService.createForum(new Forum());
        System.out.println("--------------------------------------------------");
        forumService.removeTopic(0);

    }
}
