# Product API Documentation

This document provides details about the REST API endpoints available in the Product microservice for managing products.

## Base URL

All endpoints are prefixed with `/products`.

---

## Endpoints

### 1. **Create Product**

**POST** `/products`

Creates a new product.

#### Request Body:

```json
{
  "description": "string",
  "category": "string",
  "quantity": "integer",
  "price": "integer",
  "notes": "string"
}
```

#### Responses:

- **201 Created**: Product created successfully.

---

### 2. **Create Products in Batch**

**POST** `/products/batch`

Creates multiple products in a single request.

#### Request Body:

```json
[
  {
    "name": "string",
    "description": "string",
    "category": "string",
    "quantity": "integer",
    "price": "integer",
    "notes": "string"
  }
]
```

#### Responses:

- **201 Created**: Products created successfully.

---

### 3. **Update Product**

**PUT** `/products/{id}`

Updates the details of an existing product.

#### Path Parameters:

- `id` (required): The ID of the product to update.

#### Request Body:

```json
{
  "name": "string",
  "description": "string",
  "category": "string",
  "quantity": "integer",
  "price": "integer",
  "notes": "string"
}
```

#### Responses:

- **200 OK**: Product updated successfully.
- **404 Not Found**: Product with the specified ID not found.

---

### 4. **Delete Product**

**DELETE** `/products/{id}`

Deletes a product by its ID.

#### Path Parameters:

- `id` (required): The ID of the product to delete.

#### Responses:

- **204 No Content**: Product deleted successfully.
- **404 Not Found**: Product with the specified ID not found.

---

### 5. **Get All Products**

**GET** `/products`

Retrieves a list of all products.

#### Responses:

- **200 OK**: List of all products.

#### Response Body Example:

```json
[
  {
    "name": "string",
    "description": "string",
    "category": "string",
    "quantity": "integer",
    "price": "integer",
    "notes": "string"
  }
]
```

---

### 6. **Get Product by ID**

**GET** `/products/{id}`

Retrieves a product by its ID.

#### Path Parameters:

- `id` (required): The ID of the product to retrieve.

#### Responses:

- **200 OK**: Product details.
- **404 Not Found**: Product with the specified ID not found.

#### Response Body Example:

```json
{
  "name": "string",
  "description": "string",
  "category": "string",
  "quantity": "integer",
  "price": "integer",
  "notes": "string"
}
```