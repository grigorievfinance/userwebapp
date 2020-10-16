package org.softmaker.userwebapp.to;

import java.beans.ConstructorProperties;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

public class OrderTo extends BaseTo{

    private final LocalDateTime dateTime;

    private final String description;

    private final LocalDate deadline;

    private final boolean excess;

    @ConstructorProperties({"id", "dateTime", "description", "deadline", "excess"})
    public OrderTo(Integer id, LocalDateTime dateTime, String description, LocalDate deadline, boolean excess) {
        super(id);
        this.dateTime = dateTime;
        this.description = description;
        this.deadline = deadline;
        this.excess = excess;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public boolean isExcess() {
        return excess;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderTo orderTo = (OrderTo) o;
        return excess == orderTo.excess &&
                Objects.equals(dateTime, orderTo.dateTime) &&
                Objects.equals(description, orderTo.description) &&
                Objects.equals(deadline, orderTo.deadline);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dateTime, description, deadline, excess);
    }

    @Override
    public String toString() {
        return "OrderTo{" +
                "id=" + id +
                ", dateTime=" + dateTime +
                ", description='" + description + '\'' +
                ", deadline=" + deadline +
                ", excess=" + excess +
                '}';
    }
}
