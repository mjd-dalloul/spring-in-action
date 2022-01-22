package com.example.tutorial.repository.ingredient_repository;

import com.example.tutorial.domains.Ingredient;
import com.example.tutorial.domains.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class IngredientRepositoryImpl implements IngredientRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public IngredientRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public List<Ingredient> findAll() {
        return jdbcTemplate.query("Select * from Ingredient", this::mapRowToIngredient);
    }

    @Override
    public Optional<Ingredient> findById(String id) {
        final List<Ingredient> res = jdbcTemplate.query(
                "select * from Ingredient where id=?",
                this::mapRowToIngredient,
                id
        );
        return res.isEmpty() ? Optional.empty() : Optional.of(res.get(0));
    }

    @Override
    public Ingredient save(Ingredient ingredient) {
        jdbcTemplate.update("Insert into ingredient (id, name, type) values (?, ?, ?)",
                ingredient.getId(),
                ingredient.getName(),
                ingredient.getType().toString()
        );
        return ingredient;
    }

    private Ingredient mapRowToIngredient(ResultSet row, int rowNumber) throws SQLException {
        return new Ingredient(
                row.getString("id"),
                row.getString("name"),
                Ingredient.Type.valueOf(row.getString("type"))
        );
    }
}
