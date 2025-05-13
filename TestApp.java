package in.abc.main;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import in.abc.model.Branch;
import in.abc.model.Student;
import in.abc.util.HibernateUtil;

public class TestApp {

	public static void main(String[] args) {

		Session session = null;
		Transaction transaction = null;
		boolean flag = false;

		try {
			session = HibernateUtil.getSession();

			transaction = session.beginTransaction();
			
			Branch branch = new Branch();
			branch.setBid("B-111");
			branch.setBname("CS");
			branch.setBlocation("kolkata");
			
			Student s1 = new Student("sachin","MI", branch);
			Student s2 = new Student("dhoni", "csk", branch);
			Student s3 = new Student("kohli", "RCB", branch);
			
			session.save(s1);
			session.save(s2);
			session.save(s3);

			flag = true;

		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (flag) {
				transaction.commit();
				System.out.println("Objects saved...");
			} else {
				transaction.rollback();
				System.out.println("Objects not saved...");
			}
			HibernateUtil.closeSession(session);
			HibernateUtil.closeSessionFactory();
		}

	}
}
