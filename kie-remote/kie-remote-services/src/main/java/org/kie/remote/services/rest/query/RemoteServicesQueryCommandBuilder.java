package org.kie.remote.services.rest.query;

import static org.kie.internal.query.QueryParameterIdentifiers.ACTUAL_OWNER_ID_LIST;
import static org.kie.internal.query.QueryParameterIdentifiers.BUSINESS_ADMIN_ID_LIST;
import static org.kie.internal.query.QueryParameterIdentifiers.CREATED_BY_LIST;
import static org.kie.internal.query.QueryParameterIdentifiers.END_DATE_LIST;
import static org.kie.internal.query.QueryParameterIdentifiers.EXTERNAL_ID_LIST;
import static org.kie.internal.query.QueryParameterIdentifiers.LAST_VARIABLE_LIST;
import static org.kie.internal.query.QueryParameterIdentifiers.OLD_VALUE_LIST;
import static org.kie.internal.query.QueryParameterIdentifiers.POTENTIAL_OWNER_ID_LIST;
import static org.kie.internal.query.QueryParameterIdentifiers.PROCESS_ID_LIST;
import static org.kie.internal.query.QueryParameterIdentifiers.PROCESS_INSTANCE_ID_LIST;
import static org.kie.internal.query.QueryParameterIdentifiers.PROCESS_INSTANCE_STATUS_LIST;
import static org.kie.internal.query.QueryParameterIdentifiers.PROCESS_VERSION_LIST;
import static org.kie.internal.query.QueryParameterIdentifiers.STAKEHOLDER_ID_LIST;
import static org.kie.internal.query.QueryParameterIdentifiers.START_DATE_LIST;
import static org.kie.internal.query.QueryParameterIdentifiers.TASK_ID_LIST;
import static org.kie.internal.query.QueryParameterIdentifiers.TASK_STATUS_LIST;
import static org.kie.internal.query.QueryParameterIdentifiers.VALUE_LIST;
import static org.kie.internal.query.QueryParameterIdentifiers.VARIABLE_ID_LIST;
import static org.kie.internal.query.QueryParameterIdentifiers.VAR_VALUE_ID_LIST;
import static org.kie.internal.query.QueryParameterIdentifiers.VAR_VAL_SEPARATOR;
import static org.kie.internal.query.QueryParameterIdentifiers.WORK_ITEM_ID_LIST;

import java.util.Date;

import org.jbpm.process.audit.command.AuditVariableInstanceLogQueryCommand;
import org.jbpm.services.task.commands.TaskQueryDataCommand;
import org.kie.api.task.model.Status;
import org.kie.internal.query.AbstractQueryBuilderImpl;
import org.kie.internal.query.data.QueryData;
import org.kie.internal.runtime.manager.audit.query.NodeInstanceLogQueryBuilder;
import org.kie.internal.runtime.manager.audit.query.ProcessInstanceLogQueryBuilder;
import org.kie.internal.runtime.manager.audit.query.VariableInstanceLogQueryBuilder;
import org.kie.internal.runtime.manager.audit.query.NodeInstanceLogQueryBuilder.OrderBy;
import org.kie.internal.task.query.TaskQueryBuilder;

/**
 * This is the {@link AbstractQueryBuilderImpl} implementation used by the REST query operations
 * to create the queries (the {@link QueryData} instances) needed. 
 */
class RemoteServicesQueryCommandBuilder extends AbstractQueryBuilderImpl<RemoteServicesQueryCommandBuilder> {

    private final String taskUserId;
    
    public RemoteServicesQueryCommandBuilder() {
        this.taskUserId = null;
        intersect();
    }
    
    public RemoteServicesQueryCommandBuilder(String userId) { 
        this.taskUserId = userId;
        intersect();
    }

    // process related criteria
   
    /**
     * Add one or more deployment ids as a criteria to the query
     * @param deploymentId
     * @return the current instance
     */
    public RemoteServicesQueryCommandBuilder deploymentId( String... deploymentId ) {
        addObjectParameter(EXTERNAL_ID_LIST, "deployment id", deploymentId);
        return this;
    }

    /**
     * Add one or more process ids as a criteria to the query
     * @param processId
     * @return the current instance
     */
    public RemoteServicesQueryCommandBuilder processId( String... processId ) {
        addObjectParameter(PROCESS_ID_LIST, "process id", processId );
        return this;
    }

    /**
     * Add one or more process versions as a criteria to the query
     * @param processId
     * @return the current instance
     */
    public RemoteServicesQueryCommandBuilder processVersion( String... processVersion ) {
        addObjectParameter(PROCESS_VERSION_LIST, "process version", processVersion);
        return this;
    }

    /**
     * Add one or more process instance ids as a criteria to the query
     * @param processInstanceId
     * @return the current instance
     */
    public RemoteServicesQueryCommandBuilder processInstanceId( long... processInstanceId ) {
        addLongParameter(PROCESS_INSTANCE_ID_LIST, "process instance id", processInstanceId);
        return this;
    }

    /**
     * Add one or more process instance ids as a criteria to the query
     * @param processInstanceId
     * @return the current instance
     */
    public RemoteServicesQueryCommandBuilder processInstanceIdMin( long processInstanceId ) {
        addRangeParameter(PROCESS_INSTANCE_ID_LIST, "process instance id range, start", processInstanceId, true);
        return this;
    }

    /**
     * Add one or more process instance ids as a criteria to the query
     * @param processInstanceId
     * @return the current instance
     */
    public RemoteServicesQueryCommandBuilder processInstanceIdMax( long processInstanceId ) {
        addRangeParameter(PROCESS_INSTANCE_ID_LIST, "process instance id range, end", processInstanceId, false);
        return this;
    }
    
    /**
     * Specify one more statuses (in the form of an int) as criteria.
     * @param status one or more int statuses
     * @return The current instance of this query builder
     */
    public RemoteServicesQueryCommandBuilder processInstanceStatus(int... status) { 
        addIntParameter(PROCESS_INSTANCE_STATUS_LIST, "process instance status", status);
        return this;
    }

    public RemoteServicesQueryCommandBuilder startDate( Date... date ) {
        addObjectParameter(START_DATE_LIST, "start date", date);
        return this;
    }

    public RemoteServicesQueryCommandBuilder startDateMin( Date rangeStart ) {
        addRangeParameter(START_DATE_LIST, "start date range, start", rangeStart, true);
        return this;
    }

    public RemoteServicesQueryCommandBuilder startDateMax( Date rangeEnd ) {
        addRangeParameter(START_DATE_LIST, "start date range, end", rangeEnd, false);
        return this;
    }

    public RemoteServicesQueryCommandBuilder endDate( Date... date ) {
        addObjectParameter(END_DATE_LIST, "end date", date );
        return this;
    }

    public RemoteServicesQueryCommandBuilder endDateMin( Date rangeStart ) {
        addRangeParameter(END_DATE_LIST, "end date range, start", rangeStart, true);
        return this;
    }

    public RemoteServicesQueryCommandBuilder endDateMax( Date rangeEnd ) {
        addRangeParameter(END_DATE_LIST, "end date range, end", rangeEnd, false);
        return this;
    }

    // task related criteria
    
    public RemoteServicesQueryCommandBuilder workItemId( long... workItemId ) {
        addLongParameter(WORK_ITEM_ID_LIST, "work item id", workItemId);
        return this;
    }

    /**
     * Add one or more task ids as a criteria to the query
     * @param taskId
     * @return the current instance
     */
    public RemoteServicesQueryCommandBuilder taskId( long... taskId ) {
        addLongParameter(TASK_ID_LIST, "task id", taskId);
        return this;
    }

    /**
     * Add the start of a range of task ids as a criteria to the query
     * @param taskId the lower end of the range, inclusive
     * @return the current instance
     */
    public RemoteServicesQueryCommandBuilder taskIdMin( long taskId ) {
        addRangeParameter(TASK_ID_LIST, "task instance id range, start", taskId, true);
        return this;
    }
    
    /**
     * Add the end of a range of task ids as a criteria to the query
     * @param taskId the upper end of the range, inclusive
     * @return the current instance
     */
    public RemoteServicesQueryCommandBuilder taskIdMax( long taskId ) {
        addRangeParameter(TASK_ID_LIST, "task instance id range, start", taskId, false);
        return this;
    }
    
    /**
     * Add one or more statuses as a criteria to the query
     * @param status
     * @return the current instance
     */
    public RemoteServicesQueryCommandBuilder taskStatus( Status... status ) {
        addObjectParameter(TASK_STATUS_LIST, "task status", status);
        return this;
    }

    

    /**
     * Add one or more initiator ids as a criteria to the query
     * @param createdById
     * @return the current instance
     */
    public RemoteServicesQueryCommandBuilder initiator( String... createdById ) {
        addObjectParameter(CREATED_BY_LIST, "initiator", createdById);
        return this;
    }

    /**
     * Add one or more stakeholder ids as a criteria to the query
     * @param stakeHolderId
     * @return the current instance
     */
    public RemoteServicesQueryCommandBuilder stakeHolder( String... stakeHolderId ) {
        addObjectParameter(STAKEHOLDER_ID_LIST, "stakeholder", stakeHolderId);
        return this;
    }
    
    /**
     * Add one or more potential owner ids as a criteria to the query
     * @param stakeHolderId
     * @return the current instance
     */
    public RemoteServicesQueryCommandBuilder potentialOwner( String... potentialOwnerId ) {
        addObjectParameter(POTENTIAL_OWNER_ID_LIST, "potential owner", potentialOwnerId);
        return this;
    }

    /**
     * Add one or more (actual) task owner ids as a criteria to the query
     * @param taskOwnerId
     * @return the current instance
     */
    public RemoteServicesQueryCommandBuilder taskOwner( String... taskOwnerId ) {
        addObjectParameter(ACTUAL_OWNER_ID_LIST, "task owner", taskOwnerId);
        return this;
    }

    /**
     * Add one or more business administrator ids as a criteria to the query
     * @param businessAdminId
     * @return the current instance
     */
    public RemoteServicesQueryCommandBuilder businessAdmin( String... businessAdminId ) {
        addObjectParameter(BUSINESS_ADMIN_ID_LIST, "business admin", businessAdminId);
        return this;
    }

    public RemoteServicesQueryCommandBuilder variableId( String... variableId ) {
        addObjectParameter(VARIABLE_ID_LIST, "variable id", variableId );
        return this;
    }
   
    // variable related critera
    
    public RemoteServicesQueryCommandBuilder value( String... value ) {
        addObjectParameter(VALUE_LIST, "variable value", value );
        return this;
    }

    public RemoteServicesQueryCommandBuilder oldValue( String... oldVvalue ) {
        addObjectParameter(OLD_VALUE_LIST, "old variable value", oldVvalue );
        return this;
    }
    
    public RemoteServicesQueryCommandBuilder variableValue(String variableId, String value) {
        String varValStr = variableId.length() + VAR_VAL_SEPARATOR + variableId + VAR_VAL_SEPARATOR + value;
        addObjectParameter(VAR_VALUE_ID_LIST, "value for variable", varValStr);
        return this;
    }
    
    public RemoteServicesQueryCommandBuilder last() {
        addObjectParameter(LAST_VARIABLE_LIST, "last variable value", Boolean.TRUE.booleanValue() );
        return this;
    }

    public RemoteServicesQueryCommandBuilder orderBy( Object orderByField ) {
        if( orderByField == null ) { 
            throw new IllegalArgumentException( "A null order by criteria is invalid." );
        }
        String orderByString = null;
        if( orderByField instanceof TaskQueryBuilder.OrderBy ) { 
            switch( (TaskQueryBuilder.OrderBy) orderByField ) {
            case processInstanceId:
                orderByString = "t.taskData.processInstanceId";
                break;
            default:
                throw new UnsupportedOperationException("Unsupported order by arqument: " + orderByField.toString() );
            }
        } else if( orderByField instanceof VariableInstanceLogQueryBuilder.OrderBy ) {
            switch( (VariableInstanceLogQueryBuilder.OrderBy) orderByField ) { 
            case processInstanceId:
                orderByString = "processInstanceId";
                break;
            default:
                throw new UnsupportedOperationException("Unsupported order by arqument: " + orderByField.toString() );
            }
        } else if( orderByField instanceof ProcessInstanceLogQueryBuilder.OrderBy ) {
            switch( (ProcessInstanceLogQueryBuilder.OrderBy) orderByField ) { 
            case processInstanceId:
                orderByString = "processInstanceId";
                break;
            default:
                throw new UnsupportedOperationException("Unsupported order by arqument: " + orderByField.toString() );
            }
        }
        this.queryData.getQueryContext().setOrderBy(orderByString);
        return this;
    }
        
    // command generation
    
    public TaskQueryDataCommand createTaskQueryDataCommand() { 
        if( taskUserId == null ) { 
            throw new IllegalStateException("A user id is required to create a " + TaskQueryDataCommand.class.getSimpleName() );
        }
        TaskQueryDataCommand cmd = new TaskQueryDataCommand(getQueryData());
        cmd.setUserId(taskUserId);
        return cmd;
    }
    
    public AuditVariableInstanceLogQueryCommand createVariableInstanceLogQueryCommand() { 
        return new AuditVariableInstanceLogQueryCommand(getQueryData());
    }

    @Override
    public RemoteServicesQueryCommandBuilder clear() { 
      super.clear();
      intersect();
      return this;
    }
    
}