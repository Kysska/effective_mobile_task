package com.example.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.local.dto.VacancyDbEntity
import com.example.domain.utils.DatabaseConstants
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

@Dao
interface VacancyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertVacancy(vacancy: VacancyDbEntity): Completable

    @Delete
    fun deleteVacancy(vacancy: VacancyDbEntity): Completable

    @Query("SELECT * FROM ${DatabaseConstants.VACANCY_TABLE}")
    fun getAllFavoriteVacancies(): Observable<List<VacancyDbEntity>>

    @Query("SELECT 1 FROM ${DatabaseConstants.VACANCY_TABLE} WHERE ${DatabaseConstants.VacancyColumns.ID} = :id")
    fun isVacancyExists(id: String): Single<Boolean>
}
