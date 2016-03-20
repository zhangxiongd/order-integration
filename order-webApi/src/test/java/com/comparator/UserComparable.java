package com.comparator;

/**
 * Created by zhangxiong on 16/3/6.
 */
public class UserComparable implements Comparable<UserComparable> {

    private String name;
    private Integer age;

    public UserComparable(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public int compareTo(UserComparable o) {
        return Integer.compare(age, o.getAge());
    }

    @Override
    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) {
//            return false;
//        }
//        UserComparable ohterUser = (UserComparable) o;
//        if (name != null ? !name.equals(ohterUser.getName()) : ohterUser.getName() != null) {
//            return false;
//        }
//        return (age != null ? !age.equals(ohterUser.getAge()) : ohterUser.getAge() != null);
//
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserComparable that = (UserComparable) o;

        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        return !(age != null ? !age.equals(that.age) : that.age != null);

    }

    @Override
    public int hashCode() {
//        int result = name != null ? name.hashCode() : 0;
//        result = 31 * result + (age != null ? age.hashCode() : 0);
//        return result;

        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (age != null ? age.hashCode() : 0);
        return result;
    }
}
