package br.com.otavio.personality;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.where;

@Component
public class PersonSearch {

    private static final int LOWER_BOUND = 30_000;
    private static final int UPPER_BOUND = 300_000;
    public static final int PERCENT = 100;

    @Autowired
    private MongoTemplate template;

    public List<Person> search(Filter filter, Point userPoint) {
        Query query = createQuery(filter, userPoint);
        return template.find(query, Person.class);
    }

    private Query createQuery(Filter filter, Point point) {

        Query query = new Query();
        if (filter.isPhoto()) {
            query.addCriteria(where("photo").ne(null));
        } else {
            query.addCriteria(where("photo").is(null));
        }

        if (filter.isContact()) {
            query.addCriteria(where("contactsExchanged").gt(0));
        } else {
            query.addCriteria(where("contactsExchanged").lte(0));
        }
        query.addCriteria(where("favourite").is(filter.isFavourite()));

        if (filter.isLowerBound()) {
            query.addCriteria(where("city.point").nearSphere(point).maxDistance(LOWER_BOUND));
        } else {
            query.addCriteria(where("city.point").nearSphere(point).minDistance(UPPER_BOUND));
        }
        filter.getScore().ifPresent(s -> query.addCriteria(where("score").lte(s/ PERCENT)));
        filter.getAge().ifPresent(s -> query.addCriteria(where("age").lte(s)));
        filter.getHeight().ifPresent(s -> query.addCriteria(where("heightCM").lte(s)));

        return query;
    }
}
