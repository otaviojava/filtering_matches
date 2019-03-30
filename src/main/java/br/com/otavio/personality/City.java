package br.com.otavio.personality;

import org.springframework.data.geo.Point;

import java.util.Objects;

public class City {

    private String name;

    private Point point;

    public String getName() {
        return name;
    }

    public Point getPoint() {
        return point;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        City city = (City) o;
        return Objects.equals(name, city.name) &&
                Objects.equals(point, city.point);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, point);
    }

    @Override
    public String toString() {
        return "City{" +
                "name='" + name + '\'' +
                ", point=" + point +
                '}';
    }

    public static CityBuilder builder() {
        return new CityBuilder();
    }

    public static class CityBuilder {

        private String name;

        private Point point;

        private CityBuilder() {
        }

        public CityBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public CityBuilder withPoint(Point point) {
            this.point = point;
            return this;
        }

        public City build() {
            City city = new City();
            city.name = Objects.requireNonNull(name, "name is required");
            city.point = Objects.requireNonNull(point, "point is required");
            return city;
        }
    }
}
