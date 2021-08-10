package udodog.goGetterServer.config;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import udodog.goGetterServer.repository.querydsl.CouponQueryRepository;
import udodog.goGetterServer.repository.querydsl.EventQueryRepository;
import udodog.goGetterServer.repository.querydsl.message.MessageQueryRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@TestConfiguration
public class TestConfig {

    @PersistenceContext
    private EntityManager entityManager;

    @Bean
    public JPAQueryFactory jpaQueryFactory(){
        return new JPAQueryFactory(entityManager);
    }

    @Bean
    public EventQueryRepository eventQueryRepository(){
        return new EventQueryRepository(jpaQueryFactory());
    }

    @Bean
    public CouponQueryRepository queryRepository(){
        return new CouponQueryRepository(jpaQueryFactory());
    }

    @Bean
    public MessageQueryRepository messageQueryRepository(){
        return new MessageQueryRepository(jpaQueryFactory());
    }


}
