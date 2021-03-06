---
title: Release notes
tags: [getting_started]
keywords: release notes, announcements, what's new, new features
summary: "Change log."
sidebar: jira_sidebar
permalink: release_notes.html
---

* ## **1.0.0-SNAPSHOT**
  * Initial release. (Still under active development)
  * Target JIRA API Version [6.4.13](https://docs.atlassian.com/jira/REST/6.4.13/).
  * Steps to Support
    * Component
      * jiraGetComponent
      * jiraNewComponent
      * jiraEditComponent
      * jiraGetComponentIssueCount
    * Issue
      * jiraGetIssue
      * **jiraNewIssue**
      * **jiraNewIssues**
      * **jiraEditIssue**
      * jiraAssignIssue
    * Comments
      * jiraGetComments
      * jiraAddComment
      * jiraEditComment
      * jiraGetComment
    * Email
      * jiraNotifyIssue
    * Transitions
      * jiraGetTransitions
      * jiraTransitionIssue
    * Watchers
      * jiraGetWatches
      * jiraAddWatcher
    * Project
      * jiraGetProjects
      * jiraGetProject
    * Versions
      * jiraGetVersion
      * jiraNewVersion
      * jiraEditVersion
    * IssueLinks
      * jiraLinkIssues
    * IssueLinkTypes
      * jiraGetIssueLinkTypes
    * Search
      * jiraJqlSearch

  * ## TODO
    * Projects
      * jiraGetVersions
      * jiraGetComponents
      * jiraGetStatuses
    * IssueLinks
      * jiraGetIssueLink
      * jiraDeleteIssueLink  
    * Session - Need to think on how we can reuse session Id. (Or do we need it at all.?)
    * Support Issue Custom Fields.


{% include links.html %}
