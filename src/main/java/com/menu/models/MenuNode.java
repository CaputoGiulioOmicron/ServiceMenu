package com.menu.models;

import java.util.List;

public class MenuNode {
	private int nodeId;
	private String nodeName;
	private String nodeType;
	private String flowType;
	private String groupType;
	private String status;
	private long startValidityTs;
	private long endValidityTs;
    private List<MenuNode> nodes;
    private String tag;
    private Resource resource;
    
	public int getNodeId() {
		return nodeId;
	}
	public void setNodeId(int nodeId) {
		this.nodeId = nodeId;
	}
	public String getNodeName() {
		return nodeName;
	}
	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}
	public String getNodeType() {
		return nodeType;
	}
	public void setNodeType(String nodeType) {
		this.nodeType = nodeType;
	}
	public String getFlowType() {
		return flowType;
	}
	public void setFlowType(String flowType) {
		this.flowType = flowType;
	}
	public String getGroupType() {
		return groupType;
	}
	public void setGroupType(String groupType) {
		this.groupType = groupType;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public long getStartValidityTs() {
		return startValidityTs;
	}
	public void setStartValidityTs(int startValidityTs) {
		this.startValidityTs = startValidityTs;
	}
	public long getEndValidityTs() {
		return endValidityTs;
	}
	public void setEndValidityTs(int endValidityTs) {
		this.endValidityTs = endValidityTs;
	}
	public List<MenuNode> getNodes() {
		return nodes;
	}
	public void setNodes(List<MenuNode> nodes) {
		this.nodes = nodes;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public Resource getResource() {
		return resource;
	}
	public void setResource(Resource resource) {
		this.resource = resource;
	}
	public MenuNode() {
		super();
		// TODO Auto-generated constructor stub
	}
    
    

}
