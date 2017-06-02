package org.thoughtslive.jenkins.plugins.jira;

import org.kohsuke.stapler.DataBoundConstructor;
import org.thoughtslive.jenkins.plugins.jira.Site.ValidationBehavior;

import hudson.Extension;
import hudson.model.AbstractDescribableImpl;
import hudson.model.Descriptor;
import hudson.util.ListBoxModel;
import lombok.Getter;

public class JiraField extends AbstractDescribableImpl<JiraField> {
	
	@Getter
	private String field;
	
	@Getter
	private ValidationBehavior validationBehavior;	
	

	@DataBoundConstructor
	public JiraField(String field, ValidationBehavior validationBehavior) {
		super();
		this.field = field;
		this.validationBehavior = validationBehavior;
	}

	public String getField() {
		return field;
	}

	public ValidationBehavior getValidationBehavior() {
		return validationBehavior;
	}	
	
	@Extension
    public static class DescriptorImpl extends Descriptor<JiraField> {
        public String getDisplayName() { return ""; }
        
		public ListBoxModel doFillValidationBehaviorItems() {
			ListBoxModel items = new ListBoxModel();
			for (ValidationBehavior behavior : ValidationBehavior.values()) {
				items.add(behavior.toString(), behavior.toString());
			}
			return items;
		}
    }
}
