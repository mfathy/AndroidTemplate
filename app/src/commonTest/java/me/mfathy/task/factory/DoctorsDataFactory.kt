package me.mfathy.task.factory

import me.mfathy.task.data.models.AccessToken
import me.mfathy.task.data.models.Doctor
import me.mfathy.task.data.models.DoctorPage
import me.mfathy.task.data.models.DoctorPageRequest
import me.mfathy.task.data.store.remote.models.DoctorItem
import me.mfathy.task.data.store.remote.models.GetDoctorsResponse
import me.mfathy.task.data.store.remote.models.NetAccessToken
import me.mfathy.task.interactors.doctors.GetDoctors

/**
 * Created by Mohammed Fathy.
 * dev.mfathy@gmail.com
 */
object DoctorsDataFactory {

    fun makeNetAccessToken(): NetAccessToken = NetAccessToken(
        DataFactory.randomString(),
        DataFactory.randomString(),
        DataFactory.randomBoolean(),
        DataFactory.randomString(),
        DataFactory.randomString(),
        DataFactory.randomInt(),
        DataFactory.randomString()
    )

    fun makeNetDoctorItem(): DoctorItem = DoctorItem(
        name = DataFactory.randomString(),
        lng = DataFactory.randomDouble(),
        lat = DataFactory.randomDouble(),
        id = DataFactory.randomString(),
        address = DataFactory.randomString()
    )

    fun makeGetDoctorResponse(): GetDoctorsResponse = GetDoctorsResponse(
        listOf(makeNetDoctorItem()),
        DataFactory.randomString()
    )

    fun makeAccessToken(): AccessToken =
        AccessToken(DataFactory.randomString(), DataFactory.randomInt())

    fun makeDoctor(): Doctor = Doctor(
        name = DataFactory.randomString(),
        lng = DataFactory.randomDouble(),
        lat = DataFactory.randomDouble(),
        id = DataFactory.randomString(),
        address = DataFactory.randomString()
    )

    fun makeDoctorPage(): DoctorPage {
        return DoctorPage(listOf(makeDoctor()), DataFactory.randomString())
    }

    fun makeDoctorPageRequest(): DoctorPageRequest {
        return DoctorPageRequest("herr", 0.0, 0.0, DataFactory.randomString())
    }

    fun makeParams(): GetDoctors.Params {
        return GetDoctors.Params("herr", 0.0, 0.0, DataFactory.randomString())
    }
}