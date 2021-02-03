import com.google.gson.annotations.SerializedName

/*
Copyright (c) 2021 Kotlin Data Classes Generated from JSON powered by http://www.json2kotlin.com

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.

For support, please feel free to contact me at https://www.linkedin.com/in/syedabsar */


data class DetailApiResponse (

		@SerializedName("abilities") val abilities : List<Abilities>,
		@SerializedName("base_experience") val base_experience : Int,
		@SerializedName("forms") val forms : List<Forms>,
		@SerializedName("game_indices") val game_indices : List<Game_indices>,
		@SerializedName("height") val height : Int,
		@SerializedName("id") val id : Int,
		@SerializedName("is_default") val is_default : Boolean,
		@SerializedName("location_area_encounters") val location_area_encounters : String,
		@SerializedName("moves") val moves : List<Moves>,
		@SerializedName("name") val name : String,
		@SerializedName("order") val order : Int,
		@SerializedName("species") val species : Species,
		@SerializedName("sprites") val sprites : Sprites,
		@SerializedName("stats") val stats : List<Stats>,
		@SerializedName("types") val types : List<Types>,
		@SerializedName("weight") val weight : Int
)