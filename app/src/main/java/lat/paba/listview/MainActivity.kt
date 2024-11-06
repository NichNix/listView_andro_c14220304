package lat.paba.listview

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.SearchView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private var data = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        data.addAll(listOf("1", "2", "3", "4", "5"))

        val lvAdapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            data
        )
        val listView = findViewById<ListView>(R.id.lv1)
        listView.adapter = lvAdapter

        listView.setOnItemClickListener { parent, view, position, id ->
            Toast.makeText(this, "${lvAdapter.getItem(position)}", Toast.LENGTH_LONG).show()
        }

        val searchView = findViewById<SearchView>(R.id.searchView)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                lvAdapter.filter.filter(newText)
                return true
            }
        })


        val btnTambah = findViewById<Button>(R.id.btnTambah)
        btnTambah.setOnClickListener {
            val dtAkhir = data.last().toInt() + 1
            data.add(dtAkhir.toString())
            lvAdapter.notifyDataSetChanged()
        }

        val btnHapus = findViewById<Button>(R.id.btnHapus)
        btnHapus.setOnClickListener {
            if (data.isNotEmpty()) {
                data.removeFirst()
                lvAdapter.notifyDataSetChanged()
            }
        }
    }
}
