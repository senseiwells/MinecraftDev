package com.demonwav.mcdev.platform.mixin.inspection.suppress

import com.demonwav.mcdev.platform.mixin.util.MixinConstants
import com.intellij.codeInspection.InspectionSuppressor
import com.intellij.codeInspection.SuppressQuickFix
import com.intellij.psi.PsiAnnotation
import com.intellij.psi.PsiElement
import com.intellij.psi.util.parentOfType

class MixinClassCannotBeReferencedSuppressor : InspectionSuppressor {

    override fun isSuppressedFor(element: PsiElement, toolId: String): Boolean {
        if (toolId !in INSPECTIONS) {
            return false
        }

        val annotation = element.parentOfType<PsiAnnotation>() ?: return false
        return annotation.hasQualifiedName(MixinConstants.Annotations.DYNAMIC)
    }

    override fun getSuppressActions(element: PsiElement?, toolId: String): Array<SuppressQuickFix> =
        SuppressQuickFix.EMPTY_ARRAY

    companion object {
        private val INSPECTIONS = setOf("ReferenceToMixin")
    }
}