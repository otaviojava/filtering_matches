package br.com.otavio.personality;

import java.util.Optional;

public class Filter {

    static final double SCORE_MIN = 1;
    static final double SCORE_MAX = 99;
    static final int AGE_MIN = 18;
    static final int AGE_MAX = 95;
    static final int HEIGHT_MIN = 135;
    static final int HEIGHT_MAX = 210;

    private final boolean photo;

    private final boolean contact;

    private final boolean favourite;

    private final boolean lowerBound;

    private final Double score;

    private final Integer age;

    private final Integer height;


    private Filter(boolean photo, boolean contact, boolean favourite, boolean lowerBound,
                   Double score, Integer age, Integer height) {
        this.photo = photo;
        this.contact = contact;
        this.favourite = favourite;
        this.lowerBound = lowerBound;
        this.score = score;
        this.age = age;
        this.height = height;
    }

    public boolean isPhoto() {
        return photo;
    }

    public boolean isContact() {
        return contact;
    }

    public boolean isFavourite() {
        return favourite;
    }

    public boolean isLowerBound() {
        return lowerBound;
    }

    public Optional<Double> getScore() {
        return Optional.ofNullable(score);
    }

    public Optional<Integer> getAge() {
        return Optional.ofNullable(age);
    }

    public Optional<Integer> getHeight() {
        return Optional.ofNullable(height);
    }

    @Override
    public String toString() {
        return "Filter{" +
                "photo=" + photo +
                ", contact=" + contact +
                ", favourite=" + favourite +
                ", lowerBound=" + lowerBound +
                ", score=" + score +
                ", age=" + age +
                ", height=" + height +
                '}';
    }

    public static FilterBuilder builder() {
        return new FilterBuilder();
    }

    public static class FilterBuilder {


        private FilterBuilder() {
        }

        private boolean photo;

        private boolean contact;

        private boolean favourite;

        private boolean lowerBound;

        private Double score;

        private Integer age;

        private Integer height;

        public FilterBuilder withPhoto() {
            this.photo = true;
            return this;
        }

        public FilterBuilder withPhoto(boolean photo) {
            this.photo = photo;
            return this;
        }

        public FilterBuilder withContact() {
            this.contact = true;
            return this;
        }

        public FilterBuilder withContact(boolean contact) {
            this.contact = contact;
            return this;
        }

        public FilterBuilder withFavourite() {
            this.favourite = true;
            return this;
        }

        public FilterBuilder withFavourite(boolean favourite) {
            this.favourite = favourite;
            return this;
        }

        public FilterBuilder withLowerBound(boolean lowerBound) {
            this.lowerBound = lowerBound;
            return this;
        }

        public FilterBuilder lowerBound() {
            this.lowerBound = true;
            return this;
        }

        public FilterBuilder noPhoto() {
            this.photo = false;
            return this;
        }

        public FilterBuilder noContact() {
            this.contact = false;
            return this;
        }

        public FilterBuilder noFavourite() {
            this.favourite = false;
            return this;
        }

        public FilterBuilder upperBound() {
            this.lowerBound = false;
            return this;
        }

        public FilterBuilder withScore(Double score) {
            if (score != null && (score < SCORE_MIN || score > SCORE_MAX)) {
                throw new IllegalArgumentException("Score is invalid");
            }
            this.score = score;
            return this;
        }

        public FilterBuilder withAge(Integer age) {

            if (age != null && (age < AGE_MIN || age > AGE_MAX)) {
                throw new IllegalArgumentException("Age is invalid");
            }

            this.age = age;
            return this;
        }

        public FilterBuilder withHeight(Integer height) {
            this.height = height;

            if (height != null && (height < HEIGHT_MIN || height > HEIGHT_MAX)) {
                throw new IllegalArgumentException("height is invalid");
            }
            return this;
        }

        public Filter build() {
            return new Filter(photo, contact, favourite, lowerBound, score, age, height);
        }


    }

}
