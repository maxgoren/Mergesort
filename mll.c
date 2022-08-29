#include <stdio.h>
#include <stdlib.h>

typedef struct listnode {
	int info;
	struct listnode* next;
} listnode;

listnode* makenode(int info) {
	listnode* t = (listnode*)malloc(sizeof(listnode));
	t->info = info;
	t->next = (void*)0;
	return t;
}

listnode* make_random_list() {
	listnode* t = 0;
	listnode* head = 0;
	for (int i = 0; i < 10; i++) {
		t = makenode(rand() % (100 + 1) + 1);
		t->next = head;
		head = t;
	}
	return head;
}

void print_list(listnode* h) {
	for (listnode* it = h; it != 0; it = it->next)
		printf("%d ", it->info);
	puts(" ");
}

listnode* merge(listnode* a, listnode* b) {
	listnode dummy; dummy.info = -1; dummy.next = 0;
	listnode* t = &dummy;
	puts("Mergeing: ");
	printf("front: ");
	print_list(a);
	printf("back: ");
	print_list(b);
	while (a != 0 && b != 0)
	{
		if (a->info < b->info) {
			t->next = a; a = a->next; t = t->next;
		} else {
			t->next = b; b = b->next; t = t->next;
		}
	}
	t->next = (a != 0) ? a:b;
	return dummy.next;
}

listnode* mergesort(listnode* h)
{
	if (h == 0 || h->next == 0)
		return h;
	listnode* fast = h->next;
	listnode* slow = h;
	while (fast != 0 && fast->next != 0)
	{
		slow = slow->next;
		fast = fast->next->next;
	}
	listnode* front = h;
	listnode* back = slow->next;
	slow->next = 0;
	front = mergesort(front);
	back = mergesort(back);
	return merge(front, back);
}

int main()
{
	listnode* list = make_random_list();
	print_list(list);
	list = mergesort(list);
	print_list(list);
	return 0;
}