package com.vikram.blog.payload;



import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@NoArgsConstructor
@Getter
@Setter

public class CategoryDto {
        
		private Integer categoryID;
        @NotBlank
        @Size(min =3)
		private String  categoryTitle;
		@NotBlank
		@Size(min=3)
	    private String  categoryDescription;

}
