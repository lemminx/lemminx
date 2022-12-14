/*******************************************************************************
* Copyright (c) 2022 Red Hat Inc. and others.
* All rights reserved. This program and the accompanying materials
* which accompanies this distribution, and is available at
* http://www.eclipse.org/legal/epl-v20.html
*
* SPDX-License-Identifier: EPL-2.0
*
* Contributors:
*     Red Hat Inc. - initial API and implementation
*******************************************************************************/
package org.eclipse.lemminx.extensions.contentmodel.participants.codeactions.nogrammarconstraints;

import static org.eclipse.lemminx.extensions.contentmodel.participants.codeactions.nogrammarconstraints.NoGrammarConstraintsCodeAction.createXmlModelEdit;

import org.eclipse.lemminx.commons.BadLocationException;
import org.eclipse.lemminx.dom.DOMDocument;
import org.eclipse.lemminx.extensions.generators.FileContentGeneratorSettings;
import org.eclipse.lemminx.extensions.generators.xml2relaxng.RelaxNGGeneratorSettings;
import org.eclipse.lemminx.settings.SharedSettings;
import org.eclipse.lsp4j.TextDocumentEdit;

/**
 * Code action resolver participant used to:
 * 
 * <ul>
 * <li>generate the RelaxNG file for the given DOM document</li>
 * <li>generate the association relaxng in the XML to bind it with the
 * generated RelaxNG schema</li>
 * 
 * </ul>
 *
 */
public class GenerateRelaxNGSchemaCodeActionResolver extends AbstractGenerateGrammarAndAssociationResolveCodeActionParticipant {

	public static final String PARTICIPANT_ID = GenerateRelaxNGSchemaCodeActionResolver.class.getName();

	@Override
	protected TextDocumentEdit createFileEdit(String grammarFileName, DOMDocument document,
			SharedSettings sharedSettings) throws BadLocationException {
		return createXmlModelEdit(grammarFileName, null, document, sharedSettings);
	}

	@Override
	protected FileContentGeneratorSettings getFileContentGeneratorSettings() {
		return new RelaxNGGeneratorSettings();
	}
}
