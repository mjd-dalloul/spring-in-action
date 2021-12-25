package com.example.tutorial.domains;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
public class Taco {
    @NotNull
    @Size(min = 5, message = "Name length must be at least 5 characters")
    private String name;
    @NotNull
    @Size(min = 1, message = "You must select at least 1 ingredients")
    private List<Ingredient> ingredients;
}
