package pt.isep.arqsoft.GorgeousSandwich.domain.shared;

import org.apache.commons.lang.Validate;
import org.apache.commons.lang.builder.EqualsBuilder;

public class UserEmail implements IValueObject<UserEmail> {
    private String name;

    protected UserEmail(String name) {
        Validate.notNull(name, "Email name must not be null.");
        Validate.notEmpty(name, "Email name must not be empty.");
        this.name = name;
    }

    public String obtainName() {
        return this.name;
    }

    public static UserEmail valueOf(String name){
        return new UserEmail(name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserEmail other = (UserEmail) o;

        return sameValueAs(other);
    }

    @Override
    public int hashCode() {
        return this.name.hashCode();
    }

    @Override
    public boolean sameValueAs(UserEmail other) {
        return other != null && new EqualsBuilder().append(this.name, other.name).isEquals();
    }

    @Override
    public String toString() {
        return this.name;
    }
}
