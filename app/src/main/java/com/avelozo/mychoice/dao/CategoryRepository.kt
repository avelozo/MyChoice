package com.avelozo.mychoice.dao

import com.avelozo.mychoice.model.Category
import android.content.ContentValues
import android.util.Log
import com.avelozo.mychoice.dao.DatabaseContract.CategoryEntry.COLUMN_NAME_CATEGORY_NAME
import com.avelozo.mychoice.dao.DatabaseContract.CategoryEntry.COLUMN_NAME_CATEGORY_URL
import com.avelozo.mychoice.dao.DatabaseContract.CategoryEntry.COLUMN_NAME_CATEGORY_VISITS
import com.avelozo.mychoice.dao.DatabaseContract.CategoryEntry.CATEGORY_TABLE_NAME
import java.lang.Exception
import java.sql.Array


class CategoryRepository( var dbHelper: DatabaseHelper) : ICategoryRepository {

    override fun insertCategory(category : Category){

        val db = dbHelper.writableDatabase
        val values = ContentValues()
        values.put(COLUMN_NAME_CATEGORY_NAME, category.name)
        values.put(COLUMN_NAME_CATEGORY_URL, category.imageUrl)
        values.put(COLUMN_NAME_CATEGORY_VISITS, category.timesVisited)

         db.insert(CATEGORY_TABLE_NAME, null, values)

         db.close()

    }

    override fun addItemClicked(category: Category){
            val db = dbHelper.writableDatabase
            val totalTimesVisited =  category.timesVisited +1
            db.beginTransaction()
        try {

            val values = ContentValues()
            values.put(COLUMN_NAME_CATEGORY_NAME, category.name)
            values.put(COLUMN_NAME_CATEGORY_URL, category.imageUrl)
            values.put(COLUMN_NAME_CATEGORY_VISITS, totalTimesVisited)

            db.update(CATEGORY_TABLE_NAME, values, "$COLUMN_NAME_CATEGORY_NAME = '${category.name}'", null)

            db.setTransactionSuccessful()
        } catch (e : Exception){
            Log.e("MyChoiceError",e.message)
        } finally {
            db.endTransaction()
        }
    }

    override fun getCategories(): MutableList<Category> {
        val categories = mutableListOf<Category>()

        val selectQuery = "SELECT  * FROM $CATEGORY_TABLE_NAME"

        val db = dbHelper.writableDatabase
        val cursor = db.rawQuery(selectQuery, null)

        if (cursor.moveToFirst()) {
            do {
                val name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME_CATEGORY_NAME))
                val imgUrl = cursor.getString(cursor.getColumnIndex(COLUMN_NAME_CATEGORY_URL))
                val visits = cursor.getInt(cursor.getColumnIndex(COLUMN_NAME_CATEGORY_VISITS))

                categories.add(Category(name,imgUrl,visits))
            } while (cursor.moveToNext())
        }
        cursor.close()
        db.close()
        return categories
    }

}