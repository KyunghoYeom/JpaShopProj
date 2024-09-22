package hellojpa;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        //code

        try{
            List<Member> result = em.createQuery(
                    "select m from Member m where m.username like '%hello%'", Member.class
            ).getResultList();

            for (Member member : result) {
                System.out.println("member.getUsername() = " + member.getUsername());

            }



            tx.commit();
        }catch(Exception e){
        tx.rollback();
        }
        finally {
            em.close();
        }

        emf.close();
    }


}
