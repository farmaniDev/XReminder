package com.farmani.xreminder.db

import com.farmani.xreminder.model.Reminder
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.toPersistentList
import kotlinx.serialization.KSerializer
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.serialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

class ReminderListSerializer(val dataKSerializer: KSerializer<Reminder>) :
    KSerializer<PersistentList<Reminder>> {
    class PersistentListDescriptor : SerialDescriptor by serialDescriptor<List<Reminder>>()

    override val descriptor = PersistentListDescriptor()

    override fun deserialize(decoder: Decoder): PersistentList<Reminder> {
        return ListSerializer(dataKSerializer).deserialize(decoder).toPersistentList()
    }

    override fun serialize(encoder: Encoder, value: PersistentList<Reminder>) {
        return ListSerializer(dataKSerializer).serialize(encoder, value.toList())
    }
}