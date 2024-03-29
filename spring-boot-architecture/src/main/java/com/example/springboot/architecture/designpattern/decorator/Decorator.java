package com.example.springboot.architecture.designpattern.decorator;

class Decorator implements Component
{
    private Component component;
    public Decorator(Component component)
    {
        this.component=component;
    }

    @Override
    public void operation()
    {
        component.operation();
    }
}
