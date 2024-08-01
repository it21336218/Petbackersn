package com.example.buynow.presentation.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.buynow.presentation.adapter.CategoryAdapter
import com.example.buynow.presentation.adapter.CoverProductAdapter

import com.example.buynow.data.model.Category
import com.example.buynow.data.model.Product

import com.example.buynow.R
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.IOException


class ShopFragment : Fragment() {


    private lateinit var cateList:ArrayList<Category>
    private lateinit var coverProduct:ArrayList<Product>

    private lateinit var categoryAdapter: CategoryAdapter
    private lateinit var coverProductAdapter: CoverProductAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_shop, container, false)
        activity?.window?.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)

        val coverRecView_shopFrag : RecyclerView = view.findViewById(R.id.coverRecView_shopFrag)
        val categoriesRecView : RecyclerView = view.findViewById(R.id.categoriesRecView)


        cateList = arrayListOf()
        coverProduct = arrayListOf()

        setCoverData()
        setCategoryData()

        coverRecView_shopFrag.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL, false)
        coverRecView_shopFrag.setHasFixedSize(true)
        coverProductAdapter = CoverProductAdapter(activity as Context, coverProduct )
        coverRecView_shopFrag.adapter = coverProductAdapter


        categoriesRecView.layoutManager = GridLayoutManager(context,2,LinearLayoutManager.VERTICAL,false)
        categoriesRecView.setHasFixedSize(true)
        categoryAdapter = CategoryAdapter(activity as Context, cateList )
        categoriesRecView.adapter = categoryAdapter


        return view
    }

    private fun setCategoryData() {
        cateList.add(Category("Dog Accessories","https://petmart.lk/cdn/shop/products/julius-k9-muzzle-cowhide-dog-accessories-trixie-308409_640x640.jpg?v=1615988352"))
        cateList.add(Category("Cat Accessories","https://petmart.lk/cdn/shop/products/mouse-trixie-431600_640x640.jpg?v=1599902535"))
        cateList.add(Category("Dog Food","https://petmart.lk/cdn/shop/products/josi-dog-active-dry-dog-food-josera-900g-955228_640x640.webp?v=1664475914"))
        cateList.add(Category("Cat Food","https://petmart.lk/cdn/shop/products/Atlanticsalmon_1_11zon_640x640.jpg?v=1667383657"))
        cateList.add(Category("Treats","https://petmart.lk/cdn/shop/products/1395507_640x640.jpg?v=1681039022"))
        cateList.add(Category("Bands","https://petmart.lk/cdn/shop/products/1_2__11zon_11zon_640x640.jpg?v=1668506353"))
        cateList.add(Category("Pet Care","https://petmart.lk/cdn/shop/products/trixie-diapers-for-male-dogs-dog-accessories-trixie-254167_640x640.jpg?v=1589368232"))
        cateList.add(Category("Supplements","https://petmart.lk/cdn/shop/products/trixie-salmon-oil-for-dogs-and-cats-500ml-supplements-trixie-231357_640x640.webp?v=1703233585"))


    }


    fun getJsonData(context: Context, fileName: String): String? {

        val jsonString: String
        try {
            jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            return null
        }
        return jsonString
    }

    private fun setCoverData() {

        val jsonFileString = context?.let {

            getJsonData(it, "CoverProducts.json")
        }
        val gson = Gson()

        val listCoverType = object : TypeToken<List<Product>>() {}.type

        val coverD: List<Product> = gson.fromJson(jsonFileString, listCoverType)

        coverD.forEachIndexed { _, person ->
            coverProduct.add(person)

        }


    }


}


