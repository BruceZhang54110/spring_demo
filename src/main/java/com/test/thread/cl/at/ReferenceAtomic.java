package com.test.thread.cl.at;

import java.util.concurrent.atomic.AtomicReference;

public class ReferenceAtomic {
    public static void main(String[] args) {
        AtomicReference<User> atomicReference = new AtomicReference<>();
        User oldUser = new User("T", 11);
        atomicReference.set(oldUser);

        atomicReference.compareAndSet(oldUser, new User("A", 12));
        System.out.println(atomicReference.get());
    }

    static class User {
        private String name;
        private int age;

        public User(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        @Override
        public String toString() {
            return "User{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }
}
