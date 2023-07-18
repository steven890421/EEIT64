package com.example.model;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

import com.example.entity.Member;

@Component
public class MemberDAO {
	private Member member;

    private SessionFactory sessionFactory;

    public MemberDAO() {
        // 初始化 Hibernate SessionFactory
        this.sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();
    }

    public List<Member> getAllMembers() {
        // 创建 Hibernate Session
        Session session = sessionFactory.openSession();

        // 开始事务
        session.beginTransaction();

        // 查询数据库中的所有 Member 记录
        List<Member> members = session.createQuery("FROM Member", Member.class).list();

        // 提交事务
        session.getTransaction().commit();

        // 关闭 Session
        session.close();
        

        return members;
    }

    public void closeSessionFactory() {
        // 关闭 SessionFactory
        sessionFactory.close();
    }
    
    public boolean checkMemberExists(String email, String password) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Member> query = builder.createQuery(Member.class);
        Root<Member> root = query.from(Member.class);
        
        // 添加查询条件
        query.select(root).where(builder.equal(root.get("memberEmail"), email), builder.equal(root.get("memberPassword"), password));
        
        Member member = session.createQuery(query).uniqueResult();
        transaction.commit();
        return member != null;
    }
    
    public void saveMember(Member member) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            session.save(member);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
    
    public String getMemberNameByEmail(String email) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        Member member = null;

        try {
            transaction = session.beginTransaction();

            String hql = "FROM Member WHERE memberEmail = :email";
            Query query = session.createQuery(hql);
            query.setParameter("email", email);
            member = (Member) query.uniqueResult();

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        
        return member.getMemberName();
    }
    public String upDatePW( String email, String password) {
    	
    	    Member member = getMemberByEmail(email);
    	    if (member != null) {
    	        member.setMemberPassword(password);

    	        Session session = sessionFactory.openSession();
    	        Transaction transaction = null;

    	        try {
    	            transaction = session.beginTransaction();
    	            session.update(member);
    	            transaction.commit();
    	        } catch (HibernateException e) {
    	            if (transaction != null) {
    	                transaction.rollback();
    	            }
    	            e.printStackTrace();
    	        } finally {
    	            session.close();
    	        }

    	        return "updatePW";
    	    } else {
    	        return "memberNotFound"; // 或其他適當的回應
    	    }
    	}
    	
    
    public Member getMemberByEmail(String email) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        Member member = new Member();

        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("FROM Member WHERE member_email = :email");
            query.setParameter("email", email);
            member = (Member) query.uniqueResult();
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }

        return member;
    }
    
    
    
    
    
}
