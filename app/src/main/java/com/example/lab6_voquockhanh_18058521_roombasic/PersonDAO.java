package com.example.lab6_voquockhanh_18058521_roombasic;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface PersonDAO {

    @Query("select * from person")
    public List<Person> getAll();

    @Query("select * from person where id=(:id)")
    public Person getPerson(int id);

    @Query("select * from person where name=(:name)")
    public List<Person> getPersonByName(String name);

    @Insert
    public void add(Person person);

    @Delete
    public void delete(Person person);
}
