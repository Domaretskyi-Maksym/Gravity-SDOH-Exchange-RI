import axios from "axios";
import {
	ContextResponse,
	Task,
	Condition,
	Goal,
	Organization,
	newTaskPayload,
	updateTaskPayload,
	Coding
} from "@/types";

export const getContext = async (): Promise<ContextResponse> => {
	const res = await axios.get("/current-context");

	return res.data;
};

export const getTasks = async (): Promise<Task[]> => {
	const res = await axios.get("/task");

	return res.data;
};

export const createTask = async (payload: newTaskPayload): Promise<{ taskId: string }> => {
	const res = await axios.post("/task", payload);

	return res.data;
};

export const updateTask = async ({ id, ...data }: updateTaskPayload): Promise<Task> => {
	const res = await axios.put(`/task/${id}`, data);

	return res.data;
};

export const getConditions = async (): Promise<Condition[]> => {
	const res = await axios.get("/support/conditions");

	return res.data;
};

export const getGoals = async (): Promise<Goal[]> => {
	const res = await axios.get("/support/goals");

	return res.data;
};

export const getOrganizations = async (): Promise<Organization[]> => {
	const res = await axios.get("/support/organizations");

	return res.data;
};

export const getCategories = async (): Promise<Coding[]> => {
	const res = await axios.get("/mappings/categories");

	return res.data;
};

export const getRequests = async (code: string): Promise<Coding[]> => {
	const res = await axios.get(`/mappings/categories/${code}/servicerequest/codings`);

	return res.data;
};
