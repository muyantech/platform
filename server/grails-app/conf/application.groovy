// Added by the Spring Security Core plugin:
grails.plugin.springsecurity.userLookup.userDomainClassName = 'tech.muyan.security.User'
grails.plugin.springsecurity.userLookup.authorityJoinClassName = 'tech.muyan.security.UserRole'
grails.plugin.springsecurity.authority.className = 'tech.muyan.security.Role'
grails.plugin.springsecurity.authority.groupAuthorityNameField = 'authorities'
grails.plugin.springsecurity.useRoleGroups = true
grails.plugin.springsecurity.requestMap.className = 'tech.muyan.security.RequestMap'
grails.plugin.springsecurity.roleHierarchyEntryClassName = 'tech.muyan.security.RoleHierarchyEntry'
grails.plugin.springsecurity.securityConfigType = 'Requestmap'
grails.plugin.springsecurity.controllerAnnotations.staticRules = [
  [pattern: '/', access: ['permitAll']],
  [pattern: '/oauth/**', access: ['permitAll']],
  [pattern: '/error', access: ['permitAll']],
  [pattern: '/index', access: ['permitAll']],
  [pattern: '/index.gsp', access: ['permitAll']],
  //FIXME disable call /shutdown from outside world
  [pattern: '/shutdown', access: ['permitAll']],
  [pattern: '/assets/**', access: ['permitAll']],
  [pattern: '/**/js/**', access: ['permitAll']],
  [pattern: '/**/css/**', access: ['permitAll']],
  [pattern: '/**/images/**', access: ['permitAll']],
  [pattern: '/**/favicon.ico', access: ['permitAll']]
]

grails.plugin.springsecurity.filterChain.chainMap = [
  //Stateless chain
  [pattern: '/actuator/health', filters: 'none'],
  [pattern: '/**/attachment/direct/**', filters: 'none'],
  [pattern: '/**/webhook/**', filters: 'none'],
  [pattern: '/**/translations/**', filters: 'none'],
  [pattern: '/**', filters: 'JOINED_FILTERS,-exceptionTranslationFilter,-authenticationProcessingFilter,-securityContextPersistenceFilter,-rememberMeAuthenticationFilter'],

  //Traditional chain
  //[ pattern: '/**', filters: 'JOINED_FILTERS,-restTokenValidationFilter,-restExceptionTranslationFilter']
]

grails.plugin.springsecurity.rest.token.storage.useGorm = true
grails.plugin.springsecurity.rest.token.storage.gorm.tokenDomainClassName = 'tech.muyan.security.AuthenticationToken'

// Reject with 403 rather than 500:
// https://grails-plugins.github.io/grails-spring-security-core/4.0.x/index.html
grails.plugin.springsecurity.rejectIfNoRule = true
grails.plugin.springsecurity.fii.rejectPublicInvocations = false
// https://grails-plugins.github.io/grails-spring-security-rest/3.0.1/docs/index.html#_registering_an_event_listener
// Enable security event listener to lock user after 5 failed login attempts
grails.plugin.springsecurity.useSecurityEventListener = true

// https://docs.grails.org/latest/guide/theWebLayer.html
// Don't trim all Strings at binding time to avoid unnecessary domain update when importing codes
// the default value is true
grails.databinding.trimStrings = false

// Added by the Audit-Logging plugin:
grails.plugin.auditLog.auditDomainClassName = 'tech.muyan.audit.AuditTrail'
grails.plugin.auditLog.excluded = ['version', 'lastUpdated', 'lastUpdatedBy', 'dateCreated', 'createdBy']
grails.plugin.auditLog.defaultActor = '0'

environments {
  test {
    grails.plugin.auditLog.disabled = true
  }
}
