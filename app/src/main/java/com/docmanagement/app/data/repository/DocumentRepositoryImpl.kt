package com.docmanagement.app.data.repository

import com.docmanagement.app.data.db.dao.DocumentDao
import com.docmanagement.app.data.db.entity.DocumentEntity
import com.docmanagement.app.domain.model.Document
import com.docmanagement.app.domain.model.DocumentCategory
import com.docmanagement.app.domain.model.DocumentStatus
import com.docmanagement.app.domain.model.DocumentType
import com.docmanagement.app.domain.repository.DocumentRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.Date

class DocumentRepositoryImpl(private val documentDao: DocumentDao) : DocumentRepository {

    override suspend fun saveDocument(document: Document): Long {
        return documentDao.insertDocument(document.toEntity())
    }

    override suspend fun updateDocument(document: Document) {
        documentDao.updateDocument(document.toEntity())
    }

    override suspend fun deleteDocument(document: Document) {
        documentDao.deleteDocument(document.toEntity())
    }

    override suspend fun getDocumentById(id: Int): Document? {
        return documentDao.getDocumentById(id)?.toDomain()
    }

    override fun getAllDocuments(): Flow<List<Document>> {
        return documentDao.getAllDocuments().map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override fun getDocumentsByStatus(status: String): Flow<List<Document>> {
        return documentDao.getDocumentsByStatus(status).map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override fun getDocumentsByType(type: String): Flow<List<Document>> {
        return documentDao.getDocumentsByType(type).map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override fun getDocumentsByCategory(category: String): Flow<List<Document>> {
        return documentDao.getDocumentsByCategory(category).map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override fun getDocumentsByDepartment(department: String): Flow<List<Document>> {
        return documentDao.getDocumentsByDepartment(department).map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override fun searchDocuments(query: String): Flow<List<Document>> {
        return documentDao.searchDocuments("%$query%").map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override suspend fun getDocumentCount(): Int {
        return documentDao.getDocumentCount()
    }

    override suspend fun getTodayDocumentCount(): Int {
        return documentDao.getTodayDocumentCount()
    }

    private fun Document.toEntity(): DocumentEntity {
        return DocumentEntity(
            id = id,
            fileName = fileName,
            filePath = filePath,
            documentType = documentType.name,
            category = category.name,
            extractedText = extractedText,
            routedTo = routedTo,
            status = status.name,
            createdAt = createdAt,
            updatedAt = updatedAt,
            notes = notes
        )
    }

    private fun DocumentEntity.toDomain(): Document {
        return Document(
            id = id,
            fileName = fileName,
            filePath = filePath,
            documentType = DocumentType.valueOf(documentType),
            category = DocumentCategory.valueOf(category),
            extractedText = extractedText,
            routedTo = routedTo,
            status = DocumentStatus.valueOf(status),
            createdAt = createdAt,
            updatedAt = updatedAt,
            notes = notes
        )
    }
}