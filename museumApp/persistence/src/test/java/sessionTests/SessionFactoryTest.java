package sessionTests;

import com.itaSS.dao.SessionFact;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class SessionFactoryTest {

    SessionFactory sessionFactory;

    @Before
    public void initialization() {
        sessionFactory = SessionFact.getSessionFactory();
        assertTrue(!sessionFactory.isClosed());
    }

    @Test
    public void openSession() {
        Session session = sessionFactory.openSession();
        assertTrue(session.isOpen());
        session.close();
        assertTrue(!session.isOpen());
    }

    @After
    public void closeFactory() {
        sessionFactory.close();
        assertTrue(sessionFactory.isClosed());
    }

}
