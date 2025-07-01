import android.util.Log
import com.pmdm.examen_extraordinaria.data_remote.RetrofitClient
import com.pmdm.examen_extraordinaria.model.DatosReceta
import com.pmdm.examen_extraordinaria.model.Recetas

class RecetaRepository {
    private val apiService = RetrofitClient.apiService

    suspend fun obtenerRecetasTodos(): List<Recetas> {
        return try {
            val response = apiService.getAllRecipes()
            Log.d("API_RESPONSE","Response $response")
            listOf(response) //
        } catch (e: Exception) {
            Log.e("API_ERROR", "Error en la llamada", e)
            emptyList()
        }
    }

    suspend fun obtenerRecetasFaciles(): List<Recetas> {
        return try {
            val response = apiService.getAllRecipes()
            Log.d("API_RESPONSE","Response $response")
            // Filtrar recetas por dificultad "Easy"
            val recetasFaciles = response.recipes.filter { it.difficulty.equals("Easy", ignoreCase = true) }
            listOf(Recetas(recetasFaciles))
        } catch (e: Exception) {
            Log.e("API_ERROR", "Error en la llamada", e)
            emptyList()
        }
    }

    suspend fun obtenerRecetasMedium(): List<Recetas> {
        return try {
            val response = apiService.getAllRecipes()
            Log.d("API_RESPONSE","Response $response")
            // Filtrar recetas por dificultad "Medium"
            val recetasMedium = response.recipes.filter { it.difficulty.equals("Medium", ignoreCase = true) }
            listOf(Recetas(recetasMedium))
        } catch (e: Exception) {
            Log.e("API_ERROR", "Error en la llamada", e)
            emptyList()
        }
    }


}
