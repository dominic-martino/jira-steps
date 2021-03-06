package org.thoughtslive.jenkins.plugins.jira.steps;

import static org.thoughtslive.jenkins.plugins.jira.util.Common.buildErrorResponse;

import javax.inject.Inject;

import org.jenkinsci.plugins.workflow.steps.StepContextParameter;
import org.kohsuke.stapler.DataBoundConstructor;
import org.thoughtslive.jenkins.plugins.jira.api.Comment;
import org.thoughtslive.jenkins.plugins.jira.api.ResponseData;
import org.thoughtslive.jenkins.plugins.jira.util.JiraStepDescriptorImpl;
import org.thoughtslive.jenkins.plugins.jira.util.JiraStepExecution;

import hudson.EnvVars;
import hudson.Extension;
import hudson.Util;
import hudson.model.Run;
import hudson.model.TaskListener;
import lombok.Getter;

/**
 * Step to update JIRA issue comment.
 * 
 * @author Naresh Rayapati
 *
 */
public class EditCommentStep extends BasicJiraStep {

	private static final long serialVersionUID = -6330276534463853856L;

	@Getter
	private final String idOrKey;

	@Getter
	private final int commentId;

	@Getter
	private final String comment;

	@DataBoundConstructor
	public EditCommentStep(final String idOrKey, final int commentId, final String comment) {
		this.idOrKey = idOrKey;
		this.commentId = commentId;
		this.comment = comment;
	}

	@Extension
	public static class DescriptorImpl extends JiraStepDescriptorImpl {

		public DescriptorImpl() {
			super(Execution.class);
		}

		@Override
		public String getFunctionName() {
			return "jiraEditComment";
		}

		@Override
		public String getDisplayName() {
			return getPrefix() + "Edit Issue Comment";
		}

		@Override
		public boolean isMetaStep() {
			return true;
		}
	}

	public static class Execution extends JiraStepExecution<ResponseData<Comment>> {

		private static final long serialVersionUID = -7000442485946132663L;

		@StepContextParameter
		transient Run<?, ?> run;

		@StepContextParameter
		transient TaskListener listener;

		@StepContextParameter
		transient EnvVars envVars;

		@Inject
		transient EditCommentStep step;

		@Override
		protected ResponseData<Comment> run() throws Exception {

			ResponseData<Comment> response = verifyInput();

			if (response == null) {
				logger.println("JIRA: Site - " + siteName + " - Updating comment: " + step.getComment() + " on issue: " + step.getIdOrKey());
				final String comment = addPanelMeta(step.getComment());
				response = jiraService.updateComment(step.getIdOrKey(), step.getCommentId(), comment);
			}

			return logResponse(response);
		}

		@Override
		protected <T> ResponseData<T> verifyInput() throws Exception {
			String errorMessage = null;
			ResponseData<T> response = verifyCommon(step, listener, envVars, run);

			if (response == null) {
				final String idOrKey = Util.fixEmpty(step.getIdOrKey());
				final String comment = Util.fixEmpty(step.getComment());

				if (idOrKey == null) {
					errorMessage = "idOrKey is empty or null.";
				}

				if (step.getCommentId() <= 0) {
					errorMessage = "commentId less than or equals to zero.";
				}

				if (comment == null) {
					errorMessage = "comment is empty or null.";
				}

				if (errorMessage != null) {
					response = buildErrorResponse(new RuntimeException(errorMessage));
				}
			}
			return response;
		}
	}
}
